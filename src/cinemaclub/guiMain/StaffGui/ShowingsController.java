package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Screen;
import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
import exceptions.OverlappingRuntimeException;
import exceptions.ShowingAlreadyExistsException;
import exceptions.ShowingOnOtherScreenException;
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
import java.util.ResourceBundle;

public class ShowingsController extends MainController implements Initializable {

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

    private String selectedFilm = null;
    private String selectedDate = null;
    private String selectedTime = null;
    private Integer selectedScreen = 1;

    private Showing chosenShowing;

    public void initialize(URL location, ResourceBundle resources) {
        fillFilmBox();
        //TODO: Get all screens populate screenBox
        ObservableList<Integer> dataScreen = FXCollections.observableArrayList();
        dataScreen.add(1);
        screenBox.setItems(dataScreen);
        fillShowingsTable();
        popScreensList();
    }

    public void fillFilmBox(){
        filmBox.setItems(GuiData.getFilmList(cinema));
    }

    public void pressDelete(ActionEvent event) {
        cinema.deleteShowing(chosenShowing.getScreen(), chosenShowing.getDate(), chosenShowing.getTime());
        fillShowingsTable();
    }

    public void pressViewShowing(ActionEvent event) {
        GuiData.setShowing(chosenShowing);
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_IND_SHOWING);
    }

    public void pressAddShowing(ActionEvent event) {
        //TODO: ADD TIME VALIDATOR. Change if statement into exception.
        selectedTime = timeField.getText();
        if (selectedFilm != null & selectedDate != null & selectedTime != null & selectedScreen != null) {
            try {
                cinema.addShowing(cinema.getScreen(selectedScreen), selectedDate, selectedTime, cinema.getFilmByTitle(selectedFilm));
                errorLabel.setText("New Showing of "+ selectedFilm + " Added" );
                fillShowingsTable();
            } catch (ShowingAlreadyExistsException | ShowingOnOtherScreenException | OverlappingRuntimeException e) {
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
        selectedFilm = filmBox.getSelectionModel().getSelectedItem();
    }

    public void selectScreen(ActionEvent event) {
        selectedScreen = screenBox.getSelectionModel().getSelectedItem();
    }

    public void selectDate(ActionEvent event) {
        selectedDate = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private void fillShowingsTable() {
        ObservableList <Showing> data = FXCollections.observableArrayList();
        ArrayList<Showing> showings = cinema.getAllShowings();
        data.addAll(showings);
        filmCol.setCellValueFactory(new PropertyValueFactory<>("FilmTitle"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
        screenCol.setCellValueFactory(new PropertyValueFactory<>("ScreenNumber"));
        seatsCol.setCellValueFactory(new PropertyValueFactory<>("NumberOfAvailableSeats"));
        showingTable.setItems(data);
    }

    private void popScreensList() {
        ArrayList<Screen> screensArray = cinema.getScreens();
        ArrayList<Integer> allScreenInts = new ArrayList<>();
        for (Screen screen : screensArray) {
            allScreenInts.add(screen.getScreenNumber());
        }
        ObservableList<Integer> allScreens = FXCollections.observableArrayList(allScreenInts);
        screenBox.setItems(allScreens);
    }
}
