package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.GuiData;
import exceptions.FilmExistsException;
import exceptions.ImageDoesNotExistException;
import exceptions.NoSelectionMadeException;
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

public class FilmController extends MainController implements Initializable {

    @FXML TextField titleField;
    @FXML TextArea descriptionArea;
    @FXML TextField imageField;
    @FXML TextField runtimeField;
    @FXML ImageView imageBoxEdit;
    @FXML Label titleText;
    @FXML Label descriptionText;
    @FXML Label addTitle;
    @FXML Label updateTitle;
    @FXML Button addFilm;
    @FXML Button updateFilm;
    @FXML ImageView imageBox;
    @FXML Label runtimeText;
    @FXML Label errorLabel;
    @FXML Label errorLabelFilmList;
    @FXML ListView<String> filmList;
    @FXML AnchorPane editPane;
    @FXML AnchorPane infoPane;
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

    private Film chosenFilm = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editPane.setOpacity(0);
        infoPane.setOpacity(0);
        populateFilmList();
    }
    
    private void populateFilmList(){
        filmList.setItems(GuiData.getFilmList(cinema));
    }

    public void chooseFilm(MouseEvent actionEvent) {
        try {
            chosenFilm = cinema.getFilmByTitle(filmList.getSelectionModel().getSelectedItem());
            if(chosenFilm != null) {
                filmTitle = chosenFilm.getTitle();
                filmDescription = chosenFilm.getDescription();
                filmRuntime = chosenFilm.getRunTime();
                filmImage = chosenFilm.getImagePath();
                setFilmInfo();
                infoPane.setOpacity(1);
                GuiData.setFilm(chosenFilm);
                errorLabelFilmList.setText("");
                fillShowingsTable();
            }else {
                throw new NoSelectionMadeException();
            }
        } catch (NoSelectionMadeException e) {
            errorLabelFilmList.setText(e.getMessage());
        }
    }

    public void updateFilmInfo(ActionEvent event){
        filmTitle = titleField.getText();
        filmDescription = descriptionArea.getText();
        filmRuntime = runtimeField.getText();
        filmImage = imageField.getText();
        try {
            if(!filmTitle.equals(chosenFilm.getTitle())) {
                cinema.setFilmTitle(chosenFilm, filmTitle);
            }
            cinema.setFilmDescription(chosenFilm, filmDescription);
            cinema.setFilmImagePath(chosenFilm, filmImage);
            cinema.setFilmRunTime(chosenFilm, filmRuntime);
            editPane.setOpacity(0);
            setFilmInfo();
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

            setFilmInfo();
            populateFilmList();
            editPane.setOpacity(0);
        } else{
            String errorMessage = "All fields not complete";
            errorLabel.setText(errorMessage);
            System.out.println(errorMessage);
        }
    }

    public void updateFilmPane(ActionEvent event) {
        editPane.setOpacity(1);
        updateTitle.setOpacity(1);
        addTitle.setOpacity(0);
        addFilm.setOpacity(0);
        updateFilm.setOpacity(1);
        setUpdateFilmInfo();
    }

    public void addFilmPane(ActionEvent event) {
        editPane.setOpacity(1);
        updateTitle.setOpacity(0);
        addTitle.setOpacity(1);
        addFilm.setOpacity(1);
        updateFilm.setOpacity(0);
        clearUpdate();
    }

    public void deleteFilm(ActionEvent event) {
        cinema.deleteFilm(chosenFilm.getTitle());
        populateFilmList();
        clearFilmInfo();
    }

    public void pressUploadImage(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.JPEG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterJPEG);
        File file = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            img = SwingFXUtils.toFXImage(bufferedImage, null);
            imageBoxEdit.setImage(img);
            File fSearch = new File("Images/" + file.getName());
            String fileName = file.getName();
                if(fSearch.exists()){
                    fileName = "1" + file.getName();
                }
            ImageIO.write(bufferedImage, "jpg",new File("Images/" + fileName));
            imageField.setText("/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFilmInfo(){
        File f = new File("Images/" + filmImage);
        Image img = checkImageInDirectory(f);
        imageBox.setImage(img);
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
        File f = new File("Images/" + filmImage);
        Image img = checkImageInDirectory(f);
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

    public Image checkImageInDirectory(File f) {
        try {
            if (f.exists()) {
                return new Image(filmImage);
            } else
                throw new ImageDoesNotExistException();
        } catch (ImageDoesNotExistException e) {
            errorLabel.setText(e.getMessage());
            return null;
        }
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