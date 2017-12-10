package cinemaclub.guiMain.CustomerGui;

import cinemaclub.guiMain.GuiData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ModalBookedController {

    @FXML ImageView imageBox;
    @FXML Label titleText;
    @FXML Label descriptionText;
    @FXML Label timeText;
    @FXML Label runtimeText;
    @FXML Label dateText;
    @FXML Label seatText;

    @FXML public void initialize() {
        titleText.setText(GuiData.getFilm().getTitle());
        descriptionText.setText(GuiData.getFilm().getDescription());
        Image img = new Image(GuiData.getFilm().getImagePath());
        imageBox.setImage(img);
        runtimeText.setText(GuiData.getFilm().getRunTime());
        dateText.setText(GuiData.getShowing().getDate());
        timeText.setText(GuiData.getShowing().getTime());
        String seat = Integer.toString(GuiData.getSeatNumber());
        String seatName = GuiData.getSeatRow() + seat;
        System.out.println(seatName);
        seatText.setText(seatName);
    }

    public void pressClose(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
