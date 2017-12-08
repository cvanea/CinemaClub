package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
import exceptions.FilmExistsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StaffHomeController extends StaffMainController implements Initializable {

    @FXML TextField titleField;
    @FXML TextArea descriptionArea;
    @FXML TextField imageField;
    @FXML TextField runtimeField;
    @FXML ListView<String> filmList;
    @FXML ListView<String> datesList;
    @FXML ListView<String> timesList;

    private Film chosenFilm;

    public void pressScreenView(ActionEvent actionEvent) {
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_SCREEN);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Film> films = cinema.displayAllFilms();
        ArrayList<String> filmTitles = new ArrayList<>();

        for (Film film : films) {
            filmTitles.add(film.getTitle());
        }
        ObservableList<String> data = FXCollections.observableArrayList(filmTitles);
        filmList.setItems(data);
    }

    public void chooseFilm(MouseEvent actionEvent) {
        chosenFilm = cinema.getFilmByTitle(filmList.getSelectionModel().getSelectedItem());
        setDateList();
        setFilmInfo(chosenFilm);
        GuiData.setFilm(chosenFilm);
    }

    public void chooseFilmDate(MouseEvent actionEvent) {
        setTimesList(datesList.getSelectionModel().getSelectedItem());
        GuiData.setDate(datesList.getSelectionModel().getSelectedItem());
    }

    public void chooseFilmTime(MouseEvent actionEvent) {
        GuiData.setTime(timesList.getSelectionModel().getSelectedItem());
    }

    public void updateFilmInfo(ActionEvent event) throws IOException{
        try {
            if(!titleField.getText().equals(chosenFilm.getTitle())) {
                cinema.setFilmTitle(chosenFilm, titleField.getText());
            }
            cinema.setFilmDescription(chosenFilm, descriptionArea.getText());
            cinema.setFilmImagePath(chosenFilm, imageField.getText());
            cinema.setFilmRunTime(chosenFilm, runtimeField.getText());
        } catch (FilmExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setFilmInfo(Film film){
        titleField.setText(film.getTitle());
        descriptionArea.setText(film.getDescription());
        imageField.setText(film.getImagePath());
        runtimeField.setText(film.getRunTime());
//        ArrayList<String> filmDates = new ArrayList<>();
//
//        for (Film film : films) {
//            filmTitles.add(film.getTitle());
//        }
//        ObservableList<String> data = FXCollections.observableArrayList(filmTitles);
//        filmList.setItems(data);
    }

    private void setTimesList(String date){
        ArrayList<Showing> showings = cinema.getAllShowingsByFilm(chosenFilm);
        ArrayList<String> times = new ArrayList<>();

        for (Showing showing : showings) {
            if (showing.getDate().equals(date)) {
                times.add(showing.getTime());
            }
        }

        ObservableList<String> data = FXCollections.observableArrayList(times);
        timesList.setItems(data);
    }

    private void setDateList(){
        ArrayList<Showing> showings = cinema.getAllShowingsByFilm(chosenFilm);
        ArrayList<String> dates = new ArrayList<>();

        for (Showing showing : showings) {
            dates.add(showing.getDate());
        }

        ObservableList<String> data = FXCollections.observableArrayList(dates);
        datesList.setItems(data);
    }

}
