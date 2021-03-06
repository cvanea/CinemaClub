package cinemaclub.guiMain.CustomerGui;

import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
import exceptions.SeatAlreadyTakenException;
import exceptions.SeatIsEmptyException;
import exceptions.SeatNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for booking seats view.
 */
public class BookSeatsController extends CustomerMainController implements Initializable {

    @FXML ImageView imageBox;
    @FXML Label titleText;
    @FXML Label descriptionText;
    @FXML Label timeText;
    @FXML Label runtimeText;
    @FXML Label dateText;
    @FXML Label errorLabel;
    @FXML Label screenText;
    @FXML GridPane gridSeats;

    public Showing showing;

    /**
     * Initialises the book seat view with the data od the selected showing.
     * Gets data from the Gui Data class and fill text boxes with results
     * uses GUI data to populate the display with seats and their corresponding
     * information
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showing = GuiData.getShowing();
        GuiData.setSelectedSeatMulti(new ArrayList<>());
        GuiData.setFilm(GuiData.getShowing().getFilm());
        titleText.setText(showing.getFilm().getTitle());
        descriptionText.setText(showing.getFilm().getDescription());
        runtimeText.setText(showing.getFilm().getRuntime());
        timeText.setText(showing.getTime());
        dateText.setText(showing.getDate());
        screenText.setText(showing.getScreenNumber().toString());
        GuiData.setNumberOfRows(showing.getScreen().getNumberRow());
        GuiData.setSeatsPerRow(showing.getScreen().getSeatsPerRow());
        GuiData.setupSeatButtons(gridSeats, 900, 560, "customer");
        try {
            Image img = new Image(new FileInputStream("Images" + showing.getFilm().getImagePath()));
            imageBox.setImage(img);
        } catch (IOException e) {
            errorLabel.setText("Image load error");
        }
    }

    /**
     * Creates a booking for each individual seat selected.
     * Gets an Array list of all the buttons of the selected seats from GUI Data
     * grabs the accessible text from each button collected splits the accessible
     * text into a seat row letter and seat number.
     * For each button then passes row and number parameters into the bookFilm
     * class to book eat seat.
     * Then loads the receipt modal view and changes the view back to the film
     * browser.
     * @param actionEvent press the reserve seat button to book
     */

    public void pressReserveSeat(ActionEvent actionEvent) {
        try {
            ArrayList<Button> selectedSeats = GuiData.getSelectedSeatMulti();
            if (selectedSeats.isEmpty()) {
                throw new SeatIsEmptyException();
            }

            for (Button seat : selectedSeats) {
                String seatText = seat.getAccessibleText();
                String[] splitSeat = seatText.split("(?!^)", 2);
                String seatRow = splitSeat[0];
                int seatNumber = Integer.parseInt(splitSeat[1]);
                cinema.bookFilm(showing, seatRow, seatNumber);
            }

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(BookSeatsController.class.getResource("ModalBooked.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Seats Booked");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();
            GuiData.setSelectedSeatMulti(new ArrayList<>());
            StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_FILM_VIEW);
        } catch (SeatAlreadyTakenException | SeatNotFoundException | IOException | SeatIsEmptyException e) {
            errorLabel.setText(e.getMessage());
        }
    }
}
