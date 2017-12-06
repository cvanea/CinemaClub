package cinemaclub.guiMain.CustomerGui;

import cinemaclub.cinema.Cinema;
import cinemaclub.cinema.Film;
import cinemaclub.guiMain.StageSceneNavigator;
import exceptions.PastDateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    @FXML DatePicker datePicker;
    @FXML ListView<String> filmList;
    @FXML ListView<String> timesList;

    public void selectDate(ActionEvent actionEvent) {
        try {
            String datePicked = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            ArrayList<Film> films = cinema.getFilmsByDate(datePicked);
            ArrayList<String> filmTitles = new ArrayList<>();

            for (Film film : films) {
                filmTitles.add(film.getTitle());
            }

            ObservableList<String> data = FXCollections.observableArrayList(filmTitles);
            filmList.setItems(data);
        } catch (PastDateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void chooseFilm(MouseEvent actionEvent) {
        String chosenFilm = filmList.getSelectionModel().getSelectedItem();
        ArrayList<String> times = cinema.getTimesByFilm(cinema.getFilmByTitle(chosenFilm));

        ObservableList<String> data = FXCollections.observableArrayList(times);
        timesList.setItems(data);
    }

    public void pressPickTime(ActionEvent actionEvent) {
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_BOOK_SEATS);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image img = new Image("/walle.jpg");
        imageBox.setImage(img);
    }

    public void setFilmInfo(Cinema cinema){
        titleText.setText("Title");
        descriptionText.setText("Description");
        Image img = new Image("/walle.jpg");
        imageBox.setImage(img);
    }
}
