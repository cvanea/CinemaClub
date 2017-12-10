package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
import exceptions.ShowingAlreadyExistsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class StaffShowingsController extends StaffMainController implements Initializable {

    @FXML TableView<Showing> showingTable;
    @FXML TableColumn<Showing, String> filmCol;
    @FXML TableColumn<Showing, String> dateCol;
    @FXML TableColumn<Showing, String> timeCol;
    @FXML TableColumn<Showing, String> screenCol;
    @FXML TableColumn<Showing, String> seatsCol;
    @FXML ComboBox<String> filmBox;
    @FXML ComboBox<Integer> screenBox;
    @FXML DatePicker datePicker;
    @FXML TextField timeField;
    @FXML Label errorLabel;

    private String selecFilm = null;
    private String selecDate = null;
    private String selecTime = null;
    private Integer selecScreen = 1;


    private Showing chosenShowing;

    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Film> films = cinema.displayAllFilms();
        ArrayList<String> filmTitles = new ArrayList<>();

        for (Film film : films) {
            filmTitles.add(film.getTitle());
        }
        ObservableList<String> data = FXCollections.observableArrayList(filmTitles);
        //TODO: Get all screens populate screenBox
        ObservableList<Integer> dataScreen = FXCollections.observableArrayList();
        dataScreen.add(1);
        filmBox.setItems(data);
        screenBox.setItems(dataScreen);
        fillShowingsTable();
    }

    public void pressDelete(ActionEvent event) {
        cinema.deleteShowing(chosenShowing.getDate(), chosenShowing.getTime());
    }

    public void pressViewShowing(ActionEvent event) {
        GuiData.setShowing(chosenShowing);
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_SCREEN_EDIT);
    }

    public void pressAddShowing(ActionEvent event) {
        //TODO: ADD TIME VALIDATOR
        selecTime = timeField.getText();
        if (selecFilm != null & selecDate != null & selecTime != null & selecScreen != null) {
            try {
                cinema.addShowing(selecDate, selecTime, cinema.getFilmByTitle(selecFilm));
                errorLabel.setText("New Showing of "+ selecFilm + " Added" );
                fillShowingsTable();
            } catch (ShowingAlreadyExistsException e) {
                System.out.println(e.getMessage());
                errorLabel.setText(e.getMessage());
            }
        } else {
            errorLabel.setText("Please make sure all fields are chosen before adding a new showing!");
        }
    }

    public void pressExport(ActionEvent event) {
            cinema.exportShowingsToCsv();
    }

    public void selectShowing(MouseEvent event) {
        chosenShowing = showingTable.getSelectionModel().getSelectedItem();

    }
    public void selectFilm(ActionEvent event) {
        selecFilm = filmBox.getSelectionModel().getSelectedItem();
    }

    public void selectScreen(ActionEvent event) {
        selecScreen = screenBox.getSelectionModel().getSelectedItem();
    }

    public void selectDate(ActionEvent event) {
        selecDate = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void fillShowingsTable() {
        ObservableList <Showing> data2 = FXCollections.observableArrayList();
        filmCol.setCellValueFactory(new PropertyValueFactory<>("FilmTitle"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
        screenCol.setCellValueFactory(new PropertyValueFactory<>("Screen"));
        seatsCol.setCellValueFactory(new PropertyValueFactory<>("Seat"));
        showingTable.setItems(data2);
    }
}
