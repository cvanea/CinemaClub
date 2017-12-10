package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.GuiData;
import exceptions.FilmExistsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
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
    @FXML TableView<Showing> filmTable;
    @FXML TableColumn<Showing, String> dateCol;
    @FXML TableColumn<Showing, String> timeCol;
    @FXML TableColumn<Showing, String> screenCol;
    @FXML TableColumn<Showing, String> seatsCol;

    private String filmTitle;
    private String filmDescription;
    private String filmRuntime;
    private String filmImage;
    private Image img;

    private Film chosenFilm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editPane.setOpacity(0);
        populateFilmList();
    }

    private void populateFilmList(){
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
        filmTitle = chosenFilm.getTitle();
        filmDescription = chosenFilm.getDescription();
        filmRuntime = chosenFilm.getRunTime();
        //TODO: Add validator for film image path
        filmImage = chosenFilm.getImagePath();
        setFilmInfo();
        fillShowingsTable();
        GuiData.setFilm(chosenFilm);
    }

    public void updateFilmInfo(ActionEvent event){
        try {
            if(!titleField.getText().equals(chosenFilm.getTitle())) {
                cinema.setFilmTitle(chosenFilm, titleField.getText());
            }
            cinema.setFilmDescription(chosenFilm, descriptionArea.getText());
            cinema.setFilmImagePath(chosenFilm, imageField.getText());
            cinema.setFilmRunTime(chosenFilm, runtimeField.getText());
            editPane.setOpacity(0);
            setUpdateFilmInfo();
        } catch (FilmExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void pressAddFilm(ActionEvent event){
        filmTitle = titleField.getText();
        filmDescription = descriptionArea.getText();
        filmRuntime = runtimeField.getText();
        filmImage = imageField.getText();
        if (filmTitle != null & filmDescription != null & filmRuntime != null & filmImage != null) {
            try {
                cinema.addFilm(filmTitle,filmImage , filmDescription, filmRuntime);
            } catch (FilmExistsException e) {
                System.out.println(e.getMessage());
                Film updatedFilm = cinema.getFilmByTitle(filmTitle);
                cinema.setFilmDescription(updatedFilm, filmDescription);
                cinema.setFilmImagePath(updatedFilm, filmImage);
                cinema.setFilmRunTime(updatedFilm, filmRuntime);
            }
//            Image img = new Image(filmImage);
            imageBox.setImage(img);
            titleText.setText(filmTitle);
            descriptionText.setText(filmDescription);
            runtimeText.setText(filmRuntime);
            populateFilmList();
            editPane.setOpacity(0);
        } else{
            System.out.println("All fields not complete");
        }
    }

    public void updateFilmPane(ActionEvent event) {
        editPane.setOpacity(1);
        setUpdateFilmInfo();
    }

    public void addFilmPane(ActionEvent event) {
        editPane.setOpacity(1);
        clearUpdate();
    }

    public void deleteFilm(ActionEvent event) {
        cinema.deleteFilm(chosenFilm.getTitle());
        populateFilmList();
        clearFilmInfo();
    }

    public void pressUploadImage(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            img = SwingFXUtils.toFXImage(bufferedImage, null);
            imageBoxEdit.setImage(img);
            ImageIO.write(bufferedImage, "jpg",new File("Images/" + file.getName()));
            imageField.setText("/" + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFilmInfo(){
        if (!filmImage.equals("") ) {
            Image img = new Image(filmImage);
            imageBox.setImage(img);
        }
        titleText.setText(filmTitle);
        descriptionText.setText(filmDescription);
        runtimeText.setText(filmRuntime);
    }

    private void clearFilmInfo(){
        titleText.setText("");
        descriptionText.setText("");
        imageBox.setImage(null);
        runtimeText.setText("");
    }

    private void setUpdateFilmInfo() {
        Image img = new Image(filmImage);
        imageBoxEdit.setImage(img);
        titleField.setText(filmTitle);
        descriptionArea.setText(filmDescription);
        imageField.setText(filmImage);
        runtimeField.setText(filmRuntime);
    }
    private void clearUpdate(){
        titleField.setText("");
        descriptionArea.setText("");
        imageBoxEdit.setImage(null);
        imageField.setText("");
        runtimeField.setText("");
    }

    private void fillShowingsTable() {
        ObservableList <Showing> data = FXCollections.observableArrayList();
        ArrayList<Showing> showings = cinema.getAllShowingsByFilm(chosenFilm);
        data.addAll(showings);

        dateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
        screenCol.setCellValueFactory(new PropertyValueFactory<>("ScreenNumber"));
        seatsCol.setCellValueFactory(new PropertyValueFactory<>("NumberOfAvailableSeats"));
        filmTable.setItems(data);
    }

}
