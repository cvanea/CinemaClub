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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StaffFilmController extends StaffMainController implements Initializable {

    @FXML TextField titleField;
    @FXML TextArea descriptionArea;
    @FXML TextField imageField;
    @FXML TextField runtimeField;
    @FXML ImageView imageBoxEdit;
    @FXML Label titleText;
    @FXML Label descriptionText;
    @FXML ImageView imageBox;
    @FXML Label runtimeText;
    @FXML ListView<String> filmList;
    @FXML ListView<String> datesList;
    @FXML ListView<String> timesList;
    @FXML AnchorPane editPane;

    private Film chosenFilm;

    public void pressScreenView(ActionEvent actionEvent) {
        GuiData.setShowing(cinema.getShowingByDateTime(GuiData.getDate(), GuiData.getTime()));
//        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_SCREEN);
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_SCREEN_EDIT);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editPane.setOpacity(0);
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
        setFilmInfo(chosenFilm);
        GuiData.setFilm(chosenFilm);
        //TODO: Add dates and times to table
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

    public void updateFilmPane(ActionEvent event) {
        setUpdateFilmInfo(Film film);
    }

    private void setFilmInfo(Film film){
        titleText.setText(film.getTitle());
        descriptionText.setText(film.getDescription());
        Image img = new Image(film.getImagePath());
        imageBox.setImage(img);
        runtimeText.setText(film.getRunTime());
    }

    private void setUpdateFilmInfo(Film film){
        titleField.setText(film.getTitle());
        descriptionArea.setText(film.getDescription());
        Image img = new Image(film.getImagePath());
        imageBoxEdit.setImage(img);
        runtimeField.setText(film.getRunTime());
    }

}
