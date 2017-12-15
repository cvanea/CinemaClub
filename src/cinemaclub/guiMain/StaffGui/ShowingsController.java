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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for the adding and removing showings view.
 */
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

    /**
     * Initialises the showings view with a table of showings.
     * Populates combo boxes with the correct information.
     * @param location - The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources - used to localize the root object, or null if the root object was not localized.
     */
    public void initialize(URL location, ResourceBundle resources) {
        fillFilmBox();
        ObservableList<Integer> dataScreen = FXCollections.observableArrayList();
        dataScreen.add(1);
        screenBox.setItems(dataScreen);
        fillShowingsTable();
        popScreensList();
    }

    /**
     * Fills the film combo box with movies in the database.
     */
    private void fillFilmBox(){
        filmBox.setItems(GuiData.getFilmList(cinema));
    }

    /**
     * Deletes the selected showing from the database.
     * @param event press the delete button
     */
    public void pressDelete(ActionEvent event) {
        cinema.deleteShowing(chosenShowing.getScreen(), chosenShowing.getDate(), chosenShowing.getTime());
        fillShowingsTable();
    }

    /**
     * Opens the individual showings view if a showing is selected.
     * @param event press the view showing button
     */
    public void pressViewShowing(ActionEvent event) {
        if(chosenShowing != null) {
            GuiData.setShowing(chosenShowing);
            StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_IND_SHOWING);
        } else {
            errorLabel.setText("Please select a showing to view");
            errorLabel.setStyle("-fx-text-fill: red");
        }
    }

    /**
     * Adds a new showing to the database.
     * Checks if showing information is valid .
     * if valid adds the showing to the database.
     * @param event press add showing button.
     */
    public void pressAddShowing(ActionEvent event) {
        String selectedTime = timeField.getText();
        try {
            validateTimeInput(selectedTime);
            validateAddShowing(selectedFilm, selectedDate, selectedTime, selectedScreen);
            validateDate(selectedDate);
            cinema.addShowing(cinema.getScreen(selectedScreen), selectedDate, selectedTime, cinema.getFilmByTitle(selectedFilm));
            errorLabel.setText("New Showing of " + selectedFilm + " Added");
            errorLabel.setStyle("-fx-text-fill: darkgreen");
            fillShowingsTable();
        } catch (ShowingAlreadyExistsException | ShowingOnOtherScreenException | OverlappingRuntimeException |
            MissingShowingInputsException | IncorrectTimeFormatException | PastDateException e) {
            errorLabel.setText(e.getMessage());
            errorLabel.setStyle("-fx-text-fill: red");
        }
    }

    /**
     * Exports all showings to a csv file.
     * @param event press export button.
     */
    public void pressExport(ActionEvent event) {
            cinema.exportShowingsToCsv();
            errorLabel.setText("Showings successfully exported to Showings.csv");
            errorLabel.setStyle("-fx-text-fill: darkgreen");
    }

    /**
     * Sets chosen showing when user clicks of table.
     * @param event clicks on showing.
     */
    public void selectShowing(MouseEvent event) {
        chosenShowing = showingTable.getSelectionModel().getSelectedItem();
    }

    /**
     * Selects a film from the film combo box.
     * @param event clicks on a film
     */
    public void selectFilm(ActionEvent event) {
        selectedFilm = filmBox.getSelectionModel().getSelectedItem();
    }

    /**
     * Selects a screen from the screen combo box.
     * @param event clicks on a screen
     */
    public void selectScreen(ActionEvent event) {
        selectedScreen = screenBox.getSelectionModel().getSelectedItem();
    }

    /**
     * Selects a date from the date picker.
     * Stops users adding dates in the past.
     * @param event clicks on date.
     */
    public void selectDate(ActionEvent event) {
        try {
            selectedDate = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            validateDate(selectedDate);
        } catch (PastDateException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    /**
     * Fills the table of showings.
     */
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

    /**
     * Populates the screens combo box.
     */
    private void popScreensList() {
        ArrayList<Screen> screensArray = cinema.getScreens();
        ArrayList<Integer> allScreenInts = new ArrayList<>();
        for (Screen screen : screensArray) {
            allScreenInts.add(screen.getScreenNumber());
        }
        ObservableList<Integer> allScreens = FXCollections.observableArrayList(allScreenInts);
        screenBox.setItems(allScreens);
    }

    /**
     * Checks that a showing can be added.
     * @param selectedFilm string title is in database
     * @param selectedDate string data is in valid format
     * @param selectedTime string time is in valid format and does not overlap with other showings.
     * @param selectedScreen selected screens is chosen.
     * @throws MissingShowingInputsException error message if any field is blank.
     */
    private void validateAddShowing(String selectedFilm, String selectedDate, String selectedTime, Integer selectedScreen) throws MissingShowingInputsException {
        if (selectedFilm.equals("") || selectedDate.equals("") || selectedTime.equals("") || selectedScreen == 0) {
            throw new MissingShowingInputsException();
        }
    }

    /**
     * Checks that the selected date is int he future.
     * @param date string date of showing
     * @throws PastDateException error message.
     */
    private void validateDate(String date) throws PastDateException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String timeNow = LocalDateTime.now().format(formatter);

        if (date.compareTo(timeNow) < 0) {
            throw new PastDateException();
        }
    }

    /**
     * Validates the time inputs of the showing.
     * makes sure that time is in the correct format.
     * @param time string of time of showing
     * @throws IncorrectTimeFormatException error message.
     */
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

        if (Integer.parseInt(splitTime[3] + splitTime[4]) > 60) {
            throw new IncorrectTimeFormatException();
        }
    }
}
