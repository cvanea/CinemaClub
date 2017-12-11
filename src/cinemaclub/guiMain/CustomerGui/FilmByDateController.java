package cinemaclub.guiMain.CustomerGui;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Showing;
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
    @FXML Button pickSeatButton;
    @FXML DatePicker datePicker;
    @FXML AnchorPane filmDisplayPane;
    @FXML ListView<String> filmList;
    @FXML ListView<String> timesList;

    String datePicked;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pickFilm.setOpacity(0);
        filmDisplayPane.setOpacity(0);
        filmList.setOpacity(0);
        pickSeatButton.setOpacity(0);
    }

    public void selectDate(ActionEvent actionEvent) {
        //TODO HANDLE ERROR WHEN YOU SELECT A DATE WITH NO FILMS. HANDLE ERROR WHEN YOU SELECT A DATE IN THE PAST.
        try {
            datePicked = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            ArrayList<Film> films = cinema.getFilmsByDate(datePicked);
            ArrayList<String> filmTitles = new ArrayList<>();
            for (Film film : films) {
                filmTitles.add(film.getTitle());
            }
            ObservableList<String> data = FXCollections.observableArrayList(filmTitles);
            filmList.setItems(data);
            pickFilm.setOpacity(1);
            filmList.setOpacity(1);
            GuiData.setDate(datePicked);
        } catch (PastDateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void chooseFilm(MouseEvent actionEvent) {
        try {
            String chosenFilm = filmList.getSelectionModel().getSelectedItem();

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
        } catch (NullPointerException e) {
            System.out.println("There are no showings on there!");
        }
    }

    public void chooseTime(MouseEvent actionEvent) {
        GuiData.setTime(timesList.getSelectionModel().getSelectedItem());
        pickSeatButton.setOpacity(1);
    }

    public void pressPickTime(ActionEvent actionEvent) {
        //TODO MAKE CUSTOM EXCEPTION IF THEY DON'T PICK A TIME
        GuiData.setShowing(cinema.getShowingByDateTime(GuiData.getDate(), GuiData.getTime()));
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_BOOK_SEATS);
    }

    private void setFilmInfo(Film film) throws NullPointerException {
        titleText.setText(film.getTitle());
        descriptionText.setText(film.getDescription());
        runTime.setText(film.getRunTime());
        Image img = new Image(film.getImagePath());
        imageBox.setImage(img);
        GuiData.setFilm(film);
    }
}