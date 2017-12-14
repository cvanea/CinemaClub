package cinemaclub.guiMain.CustomerGui;

import cinemaclub.guiMain.GuiData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;


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
        runtimeText.setText(GuiData.getFilm().getRuntime());
        dateText.setText(GuiData.getShowing().getDate());
        timeText.setText(GuiData.getShowing().getTime());
        ArrayList<Button> purchasedButtons = GuiData.getSelectedSeatMulti();
        StringBuilder stringBuilder = new StringBuilder();
        int purchasedButtonsSize = purchasedButtons.size();
        int c = 0;
        for(Button button : purchasedButtons){
            String buttonText = button.getAccessibleText();
            stringBuilder.append(buttonText);
            c++;
            if(c != purchasedButtonsSize){
                stringBuilder.append(", ");
            }
        }
        String seat = Integer.toString(GuiData.getSeatNumber());
        String seatName = GuiData.getSeatRow() + seat;
//        System.out.println(seatName);
        seatText.setText(stringBuilder.toString());
    }

    public void pressClose(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
