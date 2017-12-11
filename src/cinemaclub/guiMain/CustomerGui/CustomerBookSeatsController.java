package cinemaclub.guiMain.CustomerGui;

import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
import exceptions.SeatAlreadyTakenException;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerBookSeatsController extends CustomerMainController implements Initializable {

    @FXML ImageView imageBox;
    @FXML Label titleText;
    @FXML Label descriptionText;
    @FXML Label timeText;
    @FXML Label runtimeText;
    @FXML Label dateText;
    @FXML Label errorLabel;
    @FXML GridPane gridSeats;

    private String seatRow;
    private int seatNumber;
    public static Button selectedSeat = null;
    public Showing showing;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showing = GuiData.getShowing();
        GuiData.setFilm(GuiData.getShowing().getFilm());
        titleText.setText(showing.getFilm().getTitle());
        descriptionText.setText(showing.getFilm().getDescription());
        runtimeText.setText(showing.getFilm().getRunTime());
        timeText.setText(showing.getTime());
        dateText.setText(showing.getDate());
        Image img = new Image(showing.getFilm().getImagePath());
        imageBox.setImage(img);
        GuiData.setupSeatButtons(gridSeats, 820, 450, "customer");
    }

    public void pressReserveSeat(ActionEvent actionEvent) throws IOException {
        try {
                String seatRow = GuiData.getSeatRow();
                int seatNumber = GuiData.getSeatNumber();
                cinema.bookFilm(showing, seatRow, seatNumber);
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(CustomerBookSeatsController.class.getResource("ModalBooked.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Seats Booked");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node) actionEvent.getSource()).getScene().getWindow());
                stage.show();
                StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_HOME);
        } catch (SeatAlreadyTakenException | SeatNotFoundException e) {
            errorLabel.setText(e.getMessage());
        }

    }
}
