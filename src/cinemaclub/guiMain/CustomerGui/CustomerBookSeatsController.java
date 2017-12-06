package cinemaclub.guiMain.CustomerGui;

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
    @FXML Button A1;

    private String seatRow;
    private int seatNumber;

    public void pressReserveSeat(ActionEvent actionEvent) throws IOException {
        //TODO PREVENT SOMEONE FROM BOOKING A TAKEN SEAT
        try {
            cinema.bookFilm(GuiData.getDate(), GuiData.getTime(), GuiData.getFilm(), cinema.getScreen(1), seatRow, seatNumber);
            System.out.println(cinema.getScreen(1));
        } catch (SeatAlreadyTakenException | SeatNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(CustomerBookSeatsController.class.getResource("ModalBooked.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Booked");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node)actionEvent.getSource()).getScene().getWindow() );
        stage.show();
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_HOME);
    }

    public void pressSeat(ActionEvent actionEvent) {
        System.out.println(cinema.getScreen(1));
        String seat = A1.getText();

        String[] splitSeat = seat.split("(?!^)");
        seatRow = splitSeat[0];
        seatNumber = Integer.parseInt(splitSeat[1]);

        try {
            if (cinema.getScreen(1).isSeatTaken(seatRow, seatNumber)) {
                errorLabel.setText("Seat taken!");
            }
        } catch (SeatNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleText.setText(GuiData.getFilm().getTitle());
        descriptionText.setText(GuiData.getFilm().getDescription());
        runtimeText.setText(GuiData.getFilm().getRunTime());
        timeText.setText(GuiData.getTime());
        dateText.setText(GuiData.getDate());
        Image img = new Image(GuiData.getFilm().getImagePath());
        imageBox.setImage(img);
    }


}
