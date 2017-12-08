package cinemaclub.guiMain.CustomerGui;

import cinemaclub.cinema.Film;
import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
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

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerHomeController extends CustomerMainController implements Initializable {

    @FXML ImageView imageBox;
    @FXML Label titleText;
    @FXML Label descriptionText;
    @FXML Label runtimeText;
    @FXML Label runTime;
    @FXML Button pickSeatButton;
    @FXML DatePicker datePicker;
    @FXML ListView<String> filmList;
    @FXML ListView<String> timesList;

    public void selectDate(ActionEvent actionEvent) {
        //TODO HANDLE ERROR WHEN YOU SELECT A DATE WITH NO FILMS. HANDLE ERROR WHEN YOU SELECT A DATE IN THE PAST.
        try {
            String datePicked = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            ArrayList<Film> films = cinema.getFilmsByDate(datePicked);
            ArrayList<String> filmTitles = new ArrayList<>();
            for (Film film : films) {
                filmTitles.add(film.getTitle());
            }
            ObservableList<String> data = FXCollections.observableArrayList(filmTitles);
            filmList.setItems(data);
            filmList.setOpacity(1);
            GuiData.setDate(datePicked);
        } catch (PastDateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void chooseFilm(MouseEvent actionEvent) {
        String chosenFilm = filmList.getSelectionModel().getSelectedItem();
        ArrayList<String> times = cinema.getTimesByFilm(cinema.getFilmByTitle(chosenFilm));

        ObservableList<String> data = FXCollections.observableArrayList(times);
        timesList.setItems(data);
        setFilmInfo(cinema.getFilmByTitle(chosenFilm));
        runtimeText.setOpacity(1);
        timesList.setOpacity(1);
    }

    public void chooseTime(MouseEvent actionEvent) {
        GuiData.setTime(timesList.getSelectionModel().getSelectedItem());
        pickSeatButton.setOpacity(1);
    }

    public void pressPickTime(ActionEvent actionEvent) {
        //TODO MAKE CUSTOM EXCEPTION IF THEY DON'T PICK A TIME
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_BOOK_SEATS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image img = new Image("/walle.jpg");
        runtimeText.setOpacity(0);
        timesList.setOpacity(0);
        filmList.setOpacity(0);
        pickSeatButton.setOpacity(0);
    }

    private void setFilmInfo(Film film){
        titleText.setText(film.getTitle());
        descriptionText.setText(film.getDescription());
        runTime.setText(film.getRunTime());
        Image img = new Image(film.getImagePath());
        imageBox.setImage(img);
        GuiData.setFilm(film);
    }
}