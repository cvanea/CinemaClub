package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.GuiData;
import exceptions.*;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private String imagePath;
    private Image image;
    
    private Film chosenFilm = null;

    /**
     * Sets the edit and info panes to hidden and populates the list of films.
     * @param location - The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources - used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editPane.setOpacity(0);
        infoPane.setOpacity(0);
        populateFilmList();
    }

    /**
     * Populates the list of films with titles
     */
    private void populateFilmList() {
        filmList.setItems(GuiData.getFilmList(cinema));
    }

    /**
     * Chooses a film when clicked by a user
     * Gets the chosen film information and sets this into the
     * film information pane.
     * Throws errors when no selection is made
     * @param actionEvent click of mouse on film list view
     */
    public void chooseFilm(MouseEvent actionEvent) {
        try {
            chosenFilm = cinema.getFilmByTitle(filmList.getSelectionModel().getSelectedItem());
            validateFilmSelection(chosenFilm);

            filmTitle = chosenFilm.getTitle();
            filmDescription = chosenFilm.getDescription();
            filmRuntime = chosenFilm.getRuntime();
            imagePath = chosenFilm.getImagePath();
            image = new Image(new FileInputStream("Images" + imagePath));
            setFilmInfo();
            infoPane.setOpacity(1);
            GuiData.setFilm(chosenFilm);
            errorLabelFilmList.setText("");
            fillShowingsTable();
        } catch (NoSelectionMadeException | FileNotFoundException e) {
            errorLabelFilmList.setText(e.getMessage());
        }
    }

    /**
     * Updates the film information in the database and updates the film information pane.
     * Checks if the film title has changed and if it has checks to see if the name is already taken in
     * the database.
     * Sets the updated film information in the database.
     * Updates the film info pane
     * Updates the list of films.
     * Throws errors if the information of the updated film is incorrect.
     * @param event press the update button
     */
    public void pressUpdateFilmInfo(ActionEvent event) {
        try {
            getFilmInputs();
            if (!filmTitle.equals(chosenFilm.getTitle())) {
                cinema.setFilmTitle(chosenFilm, filmTitle);
            }
            cinema.setFilmDescription(chosenFilm, filmDescription);
            cinema.setFilmImagePath(chosenFilm, imagePath);
            cinema.setFilmRunTime(chosenFilm, filmRuntime);
            setFilmInfo();
            editPane.setOpacity(0);
            errorLabel.setText("");
            filmList.getItems().clear();
            populateFilmList();
        } catch (FilmExistsException | MissingFilmInputsException | IncorrectTimeFormatException | ImageDoesNotExistException | FileNotFoundException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    /**
     * Press the add film button and tries to add the new film to the database.
     * Get the new films  information and try to add the film
     * Throws errors if film information is incorrect.
     * @param event press the add film button
     */
    public void pressAddFilm(ActionEvent event) {
        try {
            getFilmInputs();
            cinema.addFilm(filmTitle, imagePath, filmDescription, filmRuntime);
            setFilmInfo();
            editPane.setOpacity(0);
            errorLabel.setText("");
            filmList.getItems().clear();
            populateFilmList();
        } catch (FilmExistsException | MissingFilmInputsException | IncorrectTimeFormatException | ImageDoesNotExistException | FileNotFoundException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    /**
     * Shows the update film pane.
     * @param event press the update film button
     */
    public void updateFilmPane(ActionEvent event) {
        editPane.setOpacity(1);
        updateTitle.setOpacity(1);
        addTitle.setOpacity(0);
        addFilm.setOpacity(0);
        updateFilm.setOpacity(1);
        setUpdateFilmInfo();
    }

    /**
     * Press the add film button to reveal add film pane.
     * @param event press add film button
     */
    public void addFilmPane(ActionEvent event) {
        editPane.setOpacity(1);
        updateTitle.setOpacity(0);
        addTitle.setOpacity(1);
        addFilm.setOpacity(1);
        updateFilm.setOpacity(0);
        clearUpdate();
    }

    /**
     * Deletes the selected film.
     * gets selected film, deletes and refreshes the film list
     * @param event Press the delete film button
     */
    public void deleteFilm(ActionEvent event) {
        cinema.deleteFilm(chosenFilm.getTitle());
        populateFilmList();
        clearFilmInfo();
    }

    /**
     * Shows a modal view to upload and a new image. Then adds image to resources folder.
     * Limits a selection to JPG, JPEG and PNG.
     * Checks if the image already exists in the resources directory if so adds a 1 to the name.
     * Writes the image to the resources folder
     * @param event press the upload image button
     */
    public void pressUploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.JPEG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterJPEG);
        File file = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image img = SwingFXUtils.toFXImage(bufferedImage, null);
            imageBoxEdit.setImage(img);
            File fSearch = new File("Images/" + file.getName());
            String fileName = file.getName();
            if (fSearch.exists()) {
                fileName = "1" + file.getName();
            }
            ImageIO.write(bufferedImage, "jpg", new File("Images/" + fileName));
            imageField.setText("/" + fileName);
        } catch (IllegalArgumentException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks add new film inputs adn validates the image.
     * @throws MissingFilmInputsException error message
     * @throws IncorrectTimeFormatException error message
     * @throws ImageDoesNotExistException error message
     * @throws FileNotFoundException error message
     */
    private void getFilmInputs() throws MissingFilmInputsException, IncorrectTimeFormatException, ImageDoesNotExistException, FileNotFoundException {
        filmTitle = titleField.getText();
        filmDescription = descriptionArea.getText();
        filmRuntime = runtimeField.getText();
        imagePath = imageField.getText();
        validateRuntimeInput(filmRuntime);
        validateFilmInputs(filmTitle, filmDescription, filmRuntime, imagePath);
        File f = new File("Images/" + imagePath);
        validateImageFile(f);
        image = new Image(new FileInputStream("Images" + imagePath));
    }

    /**
     * Sets the film infomation pane.
     */
    private void setFilmInfo() {
        imageBox.setImage(image);
        titleText.setText(filmTitle);
        descriptionText.setText(filmDescription);
        runtimeText.setText(filmRuntime);
    }

    /**
     * Clears the film information pane
     */
    private void clearFilmInfo() {
        titleText.setText("");
        descriptionText.setText("");
        imageBox.setImage(null);
        runtimeText.setText("");
    }

    /**
     * Updates the film info pane with new data
     */
    private void setUpdateFilmInfo() {
        imageBoxEdit.setImage(image);
        titleField.setText(filmTitle);
        descriptionArea.setText(filmDescription);
        imageField.setText(imagePath);
        runtimeField.setText(filmRuntime);
    }

    /**
     * Clear the update/add information pane
     */
    private void clearUpdate() {
        titleField.setText("");
        descriptionArea.setText("");
        imageBoxEdit.setImage(null);
        imageField.setText("");
        runtimeField.setText("");
        errorLabel.setText("");
    }

    /**
     * Fills the showings table for a selected film.
     */
    private void fillShowingsTable() {
        ObservableList<Showing> data = FXCollections.observableArrayList();
        ArrayList<Showing> showings = cinema.getAllShowingsByFilm(chosenFilm);
        data.addAll(showings);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
        screenCol.setCellValueFactory(new PropertyValueFactory<>("ScreenNumber"));
        seatsCol.setCellValueFactory(new PropertyValueFactory<>("NumberOfAvailableSeats"));
        filmTable.setItems(data);
    }

    /**
     * Validates that all film inputs are correct. Throws corresponding error if incorrect.
     * @param filmTitle film title string
     * @param filmDescription  film description string
     * @param filmRuntime films runtime string
     * @param filmImage films image string
     * @throws MissingFilmInputsException error message if fields are empty
     */
    private void validateFilmInputs(String filmTitle, String filmDescription, String filmRuntime, String filmImage) throws MissingFilmInputsException {
        if (filmTitle.equals("") || filmDescription.equals("") || filmRuntime.equals("") || filmImage.equals("")) {
            throw new MissingFilmInputsException();
        }
    }

    /**
     * Validates that a film selection is made.
     * @param film passes in the film object
     * @throws NoSelectionMadeException  throws error message
     */
    private void validateFilmSelection(Film film) throws NoSelectionMadeException {
        if (film == null) {
            throw new NoSelectionMadeException();
        }
    }

    /**
     * Validates that runtime inputs are in the correct format.
     * Splits the runtime and performs five checks.
     * @param runTime runtime field text
     * @throws IncorrectTimeFormatException throws message
     */
    private void validateRuntimeInput(String runTime) throws IncorrectTimeFormatException {
        String[] splitTime = runTime.split("(?!^)");

        if (splitTime.length != 5) {
            throw new IncorrectTimeFormatException();
        }

        try {
            Integer.parseInt(splitTime[0]);
            Integer.parseInt(splitTime[1]);
            Integer.parseInt(splitTime[3]);
            Integer.parseInt(splitTime[4]);
        } catch (NumberFormatException e) {
            throw new IncorrectTimeFormatException();
        }

        if (!splitTime[2].equals(":")) {
            throw new IncorrectTimeFormatException();
        }

        if (Integer.parseInt(splitTime[3] + splitTime[4]) > 60) {
            throw new IncorrectTimeFormatException();
        }
    }

    /**
     * Validates whether the image file exists.
     * @param f file of image
     * @throws ImageDoesNotExistException error message
     */
    private void validateImageFile(File f) throws ImageDoesNotExistException {
        if (!f.isFile()) {
            throw new ImageDoesNotExistException();
        }
    }
}