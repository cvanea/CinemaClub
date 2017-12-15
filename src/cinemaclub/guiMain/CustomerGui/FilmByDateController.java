package cinemaclub.guiMain.CustomerGui;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
import exceptions.NoFilmsToDisplayException;
import exceptions.NoTimeSelectedException;
import exceptions.PastDateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for films by date browser view.
 */
public class FilmByDateController extends CustomerMainController implements Initializable {

    @FXML ImageView imageBox;
    @FXML Label titleText;
    @FXML Label descriptionText;
    @FXML Label runtimeText;
    @FXML Label runTime;
    @FXML Label pickFilm;
    @FXML Label errorLabel;
    @FXML Label screen;
    @FXML Label screenText;
    @FXML Button pickSeatButton;
    @FXML DatePicker datePicker;
    @FXML AnchorPane filmDisplayPane;
    @FXML ListView<String> filmList;
    @FXML ListView<String> timesList;

    private String datePicked;
    private String chosenTime;
    private Film chosenFilm;

    /**
     * Sets all controls other than the date picker to be hidden.
     * @param location - The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources - used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pickFilm.setOpacity(0);
        filmDisplayPane.setOpacity(0);
        filmList.setOpacity(0);
        pickSeatButton.setOpacity(0);
        screen.setOpacity(0);
    }

    /**
     * Method to select a date from the date picker control.
     * creates list of all films on the date selected.
     * Clears times combo box
     * Gets a list of all films and checks to see whether the film is on the date picked
     * Passes the date picked to the gui data class
     * If a date in the past is picked an error message is shown telling the users to pick
     * another date.
     * @param actionEvent when user selects a date
     */
    public void selectDate(ActionEvent actionEvent) {
        try {
            timesList.getItems().clear();
            datePicked = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            ArrayList<Film> films = cinema.getFilmsByDate(datePicked);
            ArrayList<String> filmTitles = new ArrayList<>();
            for (Film film : films) {
                String filmString = film.getTitle();
                if (!filmTitles.contains(filmString)){
                    filmTitles.add(filmString);
                }
            }
            ObservableList<String> data = FXCollections.observableArrayList(filmTitles);
            filmList.setItems(data);
            pickFilm.setOpacity(1);
            filmList.setOpacity(1);
            GuiData.setDate(datePicked);
        } catch (PastDateException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    /**
     * Selects a film from the film list view.
     * Clears times list.
     * Checks whether the selected exists
     * If the film selected is valid :
     * Generates a list of showings based on the date and film title selected
     * Passes and  displays the list of times to the list view control
     * Makes the film information pane visible
     *
     * If the film is not valid and error message is shown asking the user to pick a film
     * from the list
     * @param actionEvent when user clicks on an item in the film list view
     */
    public void chooseFilm(MouseEvent actionEvent) {
        try {
            timesList.getItems().clear();
            String chosenFilm = filmList.getSelectionModel().getSelectedItem();
            validateFilmSelected(chosenFilm);
            ArrayList<Showing> showingsTime = cinema.getAllShowingsByFilm(cinema.getFilmByTitle(chosenFilm));
            ArrayList<String> timesArrayList = new ArrayList<>();

            for (Showing showing: showingsTime) {
                if (showing.getDate().equals(datePicked)){
                    timesArrayList.add(showing.getTime());
                }
            }

            ObservableList<String> times = FXCollections.observableArrayList(timesArrayList);
            timesList.setItems(times);
            setFilmInfo(cinema.getFilmByTitle(chosenFilm));
            filmDisplayPane.setOpacity(1);
        } catch (NoFilmsToDisplayException e) {
            errorLabel.setText("No film selected.");
        }
    }

    /**
     * Action for when a user clicks on an item in the times list view.
     * Checks time is valid then if valid passes time to gui data
     * Sets the screen number text on the view
     * If no time is selected the an error message is shown
     * @param actionEvent mouse clicked on the times list view
     */

    public void chooseTime(MouseEvent actionEvent) {
        try {
            chosenTime = timesList.getSelectionModel().getSelectedItem();
            validateTimeSelected(chosenTime);
            GuiData.setTime(chosenTime);
            screenText.setText(cinema.getShowingByDateTimeFilm(datePicked, chosenTime,chosenFilm).getScreenNumber().toString());
            screen.setOpacity(1);
            pickSeatButton.setOpacity(1);
        } catch (NoTimeSelectedException e){
            errorLabel.setText(e.getMessage());
        }
    }

    /**
     * Button to go to book showing view.
     * Passes date, time and film information to gui data
     * @param actionEvent press book seat button
     */
    public void pressPickTime(ActionEvent actionEvent) {
            GuiData.setShowing(cinema.getShowingByDateTimeFilm(datePicked, chosenTime, chosenFilm));
            StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_BOOK_SEATS);
    }

    /**
     * Sets the information in the film display pane.
     * Checks whether the image loaded is valid else throws a warning message
     * passes the chosen film to the gui data class
     * @param film pass in the chosen film bases onn the film title selected from
     *             film list view
     */
    private void setFilmInfo(Film film) {
        try {
            titleText.setText(film.getTitle());
            descriptionText.setText(film.getDescription());
            runTime.setText(film.getRuntime());
            Image img = new Image(new FileInputStream("Images" + film.getImagePath()));
            imageBox.setImage(img);
            chosenFilm = film;
            GuiData.setFilm(film);
        } catch (IOException e){
            errorLabel.setText("Unable to load image");
        }
    }

    /**
     * Checks whether a film is chosen and an empty line in the list view isn't clicked.
     * @param film from list view
     * @throws NoFilmsToDisplayException throws error message
     */
    private void validateFilmSelected(String film) throws NoFilmsToDisplayException {
        if (film == null) {
            throw new NoFilmsToDisplayException();
        }
    }

    /**
     * Checks that a time is selected from the list view.
     * @param time from list view of times
     * @throws NoTimeSelectedException throws message that no time is selected
     */
    private void validateTimeSelected(String time) throws NoTimeSelectedException {
        if (time == null || time.isEmpty()) {
            throw new NoTimeSelectedException();
        }
    }
}