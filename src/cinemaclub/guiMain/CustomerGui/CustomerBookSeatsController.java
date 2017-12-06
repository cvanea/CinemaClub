package cinemaclub.guiMain.CustomerGui;

import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
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
    @FXML Button a1but;

    public void pressReserveSeat(ActionEvent actionEvent) throws IOException {
        // TODO: Add reserve seats
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(CustomerBookSeatsController.class.getResource("ModalBooked.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Booked");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node)actionEvent.getSource()).getScene().getWindow() );
        stage.show();
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_HOME);
        System.out.println("test");
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
