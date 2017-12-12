package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Screen;
import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
import exceptions.*;
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

    private String selectedFilm = "";
    private String selectedDate = "";
    private Integer selectedScreen = 0;

    private Showing chosenShowing;

    public void initialize(URL location, ResourceBundle resources) {
        fillFilmBox();
        ObservableList<Integer> dataScreen = FXCollections.observableArrayList();
        dataScreen.add(1);
        screenBox.setItems(dataScreen);
        fillShowingsTable();
        popScreensList();
    }

    private void fillFilmBox(){
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
        //TODO: ADD TIME VALIDATOR.
        String selectedTime = timeField.getText();
        try {
            validateTimeInput(selectedTime);
            validateAddShowing(selectedFilm, selectedDate, selectedTime, selectedScreen);
            cinema.addShowing(cinema.getScreen(selectedScreen), selectedDate, selectedTime, cinema.getFilmByTitle(selectedFilm));
            errorLabel.setText("New Showing of " + selectedFilm + " Added" );
            fillShowingsTable();
        } catch (ShowingAlreadyExistsException | ShowingOnOtherScreenException |
            OverlappingRuntimeException | MissingShowingInputsException | IncorrectTimeFormatException e) {
            errorLabel.setText(e.getMessage());
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

    private void validateAddShowing(String selectedFilm, String selectedDate, String selectedTime, Integer selectedScreen) throws MissingShowingInputsException {
        if (selectedFilm.equals("") || selectedDate.equals("") || selectedTime.equals("") || selectedScreen == 0) {
            throw new MissingShowingInputsException();
        }
    }

    private void validateTimeInput(String time) throws IncorrectTimeFormatException {
        String[] splitTime = time.split("(?!^)");

        if (splitTime.length != 5) {
            throw new IncorrectTimeFormatException();
        }

        try {
            Integer.parseInt(splitTime[0]);
            Integer.parseInt(splitTime[1]);
            Integer.parseInt(splitTime[3]);
            Integer.parseInt(splitTime[4]);
        } catch (NumberFormatException e) {
            throw new IncorrectTimeFormatException();
        }

        if (!splitTime[2].equals(":")) {
            throw new IncorrectTimeFormatException();
        }

        if (Integer.parseInt(splitTime[0] + splitTime[1]) > 24) {
            throw new IncorrectTimeFormatException();
        }

        if (Integer.parseInt(splitTime[0] + splitTime[1]) == 24 &&
            (Integer.parseInt(splitTime[3]) != 0 || Integer.parseInt(splitTime[4]) != 0)) {
            throw new IncorrectTimeFormatException();
        }
    }
}
