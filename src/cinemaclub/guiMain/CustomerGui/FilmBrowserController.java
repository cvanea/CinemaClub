package cinemaclub.guiMain.CustomerGui;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
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

import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getFilms();
        displayFilmsForward();
    }

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

    private void displayFilmsForward() {
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

    private void displayFilmsBack() {
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

    public void pressMoreFilms(ActionEvent event) {
        displayFilmsForward();
    }
    public void pressPreviousFilms(ActionEvent event) {
        displayFilmsBack();
    }

    public void pressBook1(ActionEvent event) {
        String date = datesBox1.getSelectionModel().getSelectedItem();
        String time = timesBox1.getSelectionModel().getSelectedItem();
        goToShowing(date, time);
    }

    public void pressBook2(ActionEvent event) {
        String date = datesBox2.getSelectionModel().getSelectedItem();
        String time = timesBox2.getSelectionModel().getSelectedItem();
        goToShowing(date, time);
    }

    public void pressBook3(ActionEvent event) {
        String date = datesBox3.getSelectionModel().getSelectedItem();
        String time = timesBox3.getSelectionModel().getSelectedItem();
        goToShowing(date, time);
    }

    private void goToShowing(String date, String time){
        try {
            validateDateTimeSelected(date, time);
            GuiData.setDate(date);
            GuiData.setTime(time);
            GuiData.setShowing(cinema.getShowingByDateTime(date, time));
            StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_BOOK_SEATS);
        } catch (EmptyDateTimeException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void displayTimesList1(ActionEvent event) {
        String dateSelected = datesBox1.getSelectionModel().getSelectedItem();
        ObservableList<String> timesComboList = timesList(dateSelected, film1);
        timesBox1.setItems(timesComboList);
        timesBox1.getSelectionModel().select(0);
    }

    public void displayTimesList2(ActionEvent event) {
        String dateSelected = datesBox2.getSelectionModel().getSelectedItem();
        ObservableList<String> timesComboList = timesList(dateSelected, film2);
        timesBox2.setItems(timesComboList);
        timesBox2.getSelectionModel().select(0);
    }

    public void displayTimesList3(ActionEvent event) {
        String dateSelected = datesBox3.getSelectionModel().getSelectedItem();
        ObservableList<String> timesComboList = timesList(dateSelected, film3);
        timesBox3.setItems(timesComboList);
        timesBox3.getSelectionModel().select(0);
    }

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

    private void setFilmInfo1(Film film) {
        titleText1.setText(film.getTitle());
        descriptionText1.setText(film.getDescription());
        runtimeText1.setText(film.getRuntime());
        Image img = new Image(film.getImagePath());
        imageBox1.setImage(img);
        ArrayList<String> datesList1 = getDatesList(film);
        ObservableList<String> datesComboList1 = FXCollections.observableArrayList(datesList1);
        datesBox1.setItems(datesComboList1);
        film1 = film;
    }

    private void setFilmInfo2(Film film){
        titleText2.setText(film.getTitle());
        descriptionText2.setText(film.getDescription());
        runtimeText2.setText(film.getRuntime());
        Image img = new Image(film.getImagePath());
        imageBox2.setImage(img);
        ArrayList<String> datesList = getDatesList(film);
        ObservableList<String> datesComboList = FXCollections.observableArrayList(datesList);
        datesBox2.setItems(datesComboList);
        film2 = film;
    }

    private void setFilmInfo3(Film film){
        titleText3.setText(film.getTitle());
        descriptionText3.setText(film.getDescription());
        runtimeText3.setText(film.getRuntime());
        Image img = new Image(film.getImagePath());
        imageBox3.setImage(img);
        ArrayList<String> datesList = getDatesList(film);
        ObservableList<String> datesComboList = FXCollections.observableArrayList(datesList);
        datesBox3.setItems(datesComboList);
        film3 = film;
    }

    private ArrayList<String> getDatesList(Film film) {
        ArrayList<Showing> filmShowing = cinema.getAllShowingsByFilm(film);
        ArrayList<String> datesList = new ArrayList<>();
        for (Showing showing: filmShowing) {
            String date = showing.getDate();
            if (!datesList.contains(date)) {
                datesList.add(date);
            }
        }
        return datesList;
    }

    private void validateDateTimeSelected(String date, String time) throws EmptyDateTimeException {
        if (date == null || time == null) {
            throw new EmptyDateTimeException();
        }
    }

}