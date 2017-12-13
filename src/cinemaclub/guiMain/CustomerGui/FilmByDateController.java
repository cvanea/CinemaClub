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

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pickFilm.setOpacity(0);
        filmDisplayPane.setOpacity(0);
        filmList.setOpacity(0);
        pickSeatButton.setOpacity(0);
        screen.setOpacity(0);
    }

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

    public void chooseTime(MouseEvent actionEvent) {
        try {
            chosenTime = timesList.getSelectionModel().getSelectedItem();
            validateTimeSelected(chosenTime);
            GuiData.setTime(chosenTime);
            screenText.setText(cinema.getShowingByDateTime(datePicked, chosenTime).getScreenNumber().toString());
            screen.setOpacity(1);
            pickSeatButton.setOpacity(1);
        } catch (NoTimeSelectedException e){
            errorLabel.setText(e.getMessage());
        }
    }

    public void pressPickTime(ActionEvent actionEvent) {
        try {
            validateTimeSelected(chosenTime);
            GuiData.setShowing(cinema.getShowingByDateTimeFilm(datePicked, chosenTime, chosenFilm));
            StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_BOOK_SEATS);
            throw new NoTimeSelectedException();
        } catch (NoTimeSelectedException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    private void setFilmInfo(Film film) {
        try {
            validateFilmSelected(film);
            titleText.setText(film.getTitle());
            descriptionText.setText(film.getDescription());
            runTime.setText(film.getRuntime());
            Image img = new Image(film.getImagePath());
            imageBox.setImage(img);
            chosenFilm = film;
            GuiData.setFilm(film);
        } catch (NoFilmsToDisplayException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    private void validateFilmSelected(Film film) throws NoFilmsToDisplayException {
        if (film.getTitle().isEmpty()) {
            throw new NoFilmsToDisplayException();
        }
    }

    private void validateFilmSelected(String film) throws NoFilmsToDisplayException {
        if (film == null) {
            throw new NoFilmsToDisplayException();
        }
    }

    private void validateTimeSelected(String time) throws NoTimeSelectedException {
        if (time == null || time.isEmpty()) {
            throw new NoTimeSelectedException();
        }
    }
}