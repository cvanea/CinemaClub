package cinemaclub.guiMain.CustomerGui;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
import cinemaclub.user.Booking;
import exceptions.EmptyDateTimeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FilmBrowserController extends CustomerMainController implements Initializable {

    @FXML ImageView imageBox1;
    @FXML Label titleText1;
    @FXML Label descriptionText1;
    @FXML Label runtimeText1;
    @FXML ComboBox<String> datesBox1;
    @FXML ComboBox<String> timesBox1;
    @FXML Button bookSeatButton1;

    @FXML ImageView imageBox2;
    @FXML Label titleText2;
    @FXML Label descriptionText2;
    @FXML Label runtimeText2;
    @FXML ComboBox<String> datesBox2;
    @FXML ComboBox<String> timesBox2;
    @FXML Button bookSeatButton2;

    @FXML ImageView imageBox3;
    @FXML Label titleText3;
    @FXML Label descriptionText3;
    @FXML Label runtimeText3;
    @FXML ComboBox<String> datesBox3;
    @FXML ComboBox<String> timesBox3;
    @FXML Button bookSeatButton3;

    @FXML Label errorLabel;

    private int numberOfFilmsWShowings;
    private int index  = 0;
    private Film film1;
    private Film film2;
    private Film film3;
    private ArrayList<Film> filmsWithShowing = new ArrayList<>();

    /**
     * Gets all films with showing Populates the view with 3 movies
     * on intilisation.
     * @param location
     * @param resources
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getFilms();
        displayFilmsForward();
    }

    /**
     * Gets all films with showings added
     * checks the number of films with showings
     */
    private void getFilms(){
        ArrayList<Showing> showings = cinema.getAllShowings();
        for (Showing showing: showings){
            Film showingFilm = showing.getFilm();
            if(!filmsWithShowing.contains(showingFilm)){
                filmsWithShowing.add(showingFilm);
            }
        }
        numberOfFilmsWShowings = filmsWithShowing.size() - 1;
    }

    /**
     * Gets the list of the next 3 films to display (more button)
     * Uses a index and counter to create sets of three films
     */
    private void displayFilmsForward() {
        clearLists();
        for (int c = 1; c < 4; c++) {
            Film film = filmsWithShowing.get(index);
            if (c == 1) {
                setFilmInfo1(film);
            } else if (c == 2) {
                setFilmInfo2(film);
            } else {
                setFilmInfo3(film);
            }

            if (index == numberOfFilmsWShowings) {
                index = 0;
            } else {
                index++;
            }
        }
    }

    /**
     * Gets the last three films to display
     * Clears teh last time date lists
     * sets the film info displays
     */
    private void displayFilmsBack() {
        clearLists();
        for (int c = 1; c < 4; c++) {
            Film film = filmsWithShowing.get(index);
            if (c == 1) {
                setFilmInfo3(film);
            } else if (c == 2) {
                setFilmInfo2(film);
            } else {
                setFilmInfo1(film);
            }

            if (index == 0) {
                index = numberOfFilmsWShowings;
            } else {
                index--;
            }
        }
    }

    /**
     * Clears the list of items int he times and date boxes
     */
    private void clearLists() {
        timesBox1.getItems().clear();
        timesBox2.getItems().clear();
        timesBox3.getItems().clear();
        datesBox1.getItems().clear();
        datesBox2.getItems().clear();
        datesBox3.getItems().clear();
    }

    /**
     * More button to see the next 3 films in the browser
     * @param event press more button
     */
    public void pressMoreFilms(ActionEvent event) {
        displayFilmsForward();
    }

    /**
     * Previous button to see the last 3 films in the browser
     * @param event press previous button
     */
    public void pressPreviousFilms(ActionEvent event) {
        displayFilmsBack();
    }

    /**
     * Book seats button in first column
     * gets the date and times from the corresponding lists and
     * passes information to the goToShowing method
     * @param event press the book seat button (on left)
     */
    public void pressBook1(ActionEvent event) {
        String date = datesBox1.getSelectionModel().getSelectedItem();
        String time = timesBox1.getSelectionModel().getSelectedItem();
        goToShowing(date, time , film1);
    }

    /**
     * Book seats button in middle column
     * gets the date and times from the corresponding lists and
     * passes information to the goToShowing method
     * @param event press the book seat button (center)
     */
    public void pressBook2(ActionEvent event) {
        String date = datesBox2.getSelectionModel().getSelectedItem();
        String time = timesBox2.getSelectionModel().getSelectedItem();
        goToShowing(date, time, film2);
    }

    /**
     * Book seats button in third column
     * gets the date and times from the corresponding lists and
     * passes information to the goToShowing method
     * @param event press the book seat button (on right)
     */
    public void pressBook3(ActionEvent event) {
        String date = datesBox3.getSelectionModel().getSelectedItem();
        String time = timesBox3.getSelectionModel().getSelectedItem();
        goToShowing(date, time, film3);
    }

    /**
     * Passes selected showing information to GUI data and changes to the booking scene
     * Validates that the date and time selected are correct, makign sure date tie isnt
     * empty
     * passes information to guidata
     * @param date pass the date information from chosen date list
     * @param time pass the time information from chosen time list
     * @param film gets the film from the book seats methods to pass
     */
    private void goToShowing(String date, String time, Film film){
        try {
            validateDateTimeSelected(date, time);
            GuiData.setDate(date);
            GuiData.setTime(time);
            GuiData.setShowing(cinema.getShowingByDateTimeFilm(date, time, film));
            StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_BOOK_SEATS);
        } catch (EmptyDateTimeException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    /**
     * Displays times after dates are selected. First List
     * Sets selected time to the first in the index array
     * @param event select a time from the dates list
     */
    public void displayTimesList1(ActionEvent event) {
        String dateSelected = datesBox1.getSelectionModel().getSelectedItem();
        ObservableList<String> timesComboList = timesList(dateSelected, film1);
        timesBox1.setItems(timesComboList);
        timesBox1.getSelectionModel().select(0);
    }

    /**
     * Displays times after dates are selected. Middle list
     * Sets selected time to the first in the index array
     * @param event select a time from the dates list
     */
    public void displayTimesList2(ActionEvent event) {
        String dateSelected = datesBox2.getSelectionModel().getSelectedItem();
        ObservableList<String> timesComboList = timesList(dateSelected, film2);
        timesBox2.setItems(timesComboList);
        timesBox2.getSelectionModel().select(0);
    }

    /**
     * Displays times after dates are selected. Right list
     * Sets selected time to the first in the index array
     * @param event select a time from the dates list
     */
    public void displayTimesList3(ActionEvent event) {
        String dateSelected = datesBox3.getSelectionModel().getSelectedItem();
        ObservableList<String> timesComboList = timesList(dateSelected, film3);
        timesBox3.setItems(timesComboList);
        timesBox3.getSelectionModel().select(0);
    }


    /**
     * Creates observable list for times
     * @param dateSelected inputs the date chosen from the corresponding date list box
     * @param film inputs the film chosen by the either of the display fims methods
     * @return observable array list of the corresponding times
     */
    private ObservableList<String> timesList(String dateSelected, Film film){
        ArrayList<Showing> showingsTime = cinema.getAllShowingsByFilm(film);
        ArrayList<String> timesList = new ArrayList<>();
        for (Showing showing: showingsTime) {
            if (showing.getDate().equals(dateSelected)){
                timesList.add(showing.getTime());
            }
        }
        return FXCollections.observableArrayList(timesList);
    }

    /**
     * Sets film info of the first pane
     * Checks to see if the image can be loaded if not throws error label
     * fills the dates combo box with dates list
     * @param film takes selected film object from display method
     */
    private void setFilmInfo1(Film film) {
        titleText1.setText(film.getTitle());
        descriptionText1.setText(film.getDescription());
        runtimeText1.setText(film.getRuntime());
        try {
            Image img = new Image(new FileInputStream("Images" + film.getImagePath()));
            imageBox1.setImage(img);
        } catch (IOException e){
            errorLabel.setText("Image load error");
        }
        ArrayList<String> datesList1 = getDatesList(film);
        ObservableList<String> datesComboList1 = FXCollections.observableArrayList(datesList1);
        datesBox1.setItems(datesComboList1);
        film1 = film;
    }

    /**
     * Sets film info of the second pane
     * Checks to see if the image can be loaded if not throws error label
     * fills the dates combo box with dates list
     * @param film takes selected film object from display method
     */
    private void setFilmInfo2(Film film){
        titleText2.setText(film.getTitle());
        descriptionText2.setText(film.getDescription());
        runtimeText2.setText(film.getRuntime());
        try {
            Image img = new Image(new FileInputStream("Images" + film.getImagePath()));
            imageBox2.setImage(img);
        } catch (IOException e){
            errorLabel.setText("Image load error");
        }
        ArrayList<String> datesList = getDatesList(film);
        ObservableList<String> datesComboList = FXCollections.observableArrayList(datesList);
        datesBox2.setItems(datesComboList);
        film2 = film;
    }

    /**
     * Sets film info of the third pane
     * Checks to see if the image can be loaded if not throws error label
     * fills the dates combo box with dates list
     * @param film takes selected film object from display method
     */
    private void setFilmInfo3(Film film){
        titleText3.setText(film.getTitle());
        descriptionText3.setText(film.getDescription());
        runtimeText3.setText(film.getRuntime());
        try {
            Image img = new Image(new FileInputStream("Images" + film.getImagePath()));
            imageBox3.setImage(img);
        } catch (IOException e){
            errorLabel.setText("Image load error");
        }
        ArrayList<String> datesList = getDatesList(film);
        ObservableList<String> datesComboList = FXCollections.observableArrayList(datesList);
        datesBox3.setItems(datesComboList);
        film3 = film;
    }

    /**
     * Creates a dates list for a films
     * checks if dates are for the corresponding film and that dates are not in the past
     * @param film takes film from display method
     * @return returns a dates string array list
     */
    private ArrayList<String> getDatesList(Film film) {
        ArrayList<Showing> filmShowing = cinema.getAllShowingsByFilm(film);
        ArrayList<String> datesList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateNow = LocalDate.now().format(formatter);
        for (Showing showing: filmShowing) {
            String date = showing.getDate();
            if (!datesList.contains(date) && date.compareTo(dateNow) > 0) {
                datesList.add(date);
            }
        }
        return datesList;
    }

    /**
     * Checks in the date and time lists are empty
     * @param date date string from dates combo box
     * @param time time string from time combo box
     * @throws EmptyDateTimeException message to say either box is empty
     */
    private void validateDateTimeSelected(String date, String time) throws EmptyDateTimeException {
        if (date == null || time == null) {
            throw new EmptyDateTimeException();
        }
    }

}