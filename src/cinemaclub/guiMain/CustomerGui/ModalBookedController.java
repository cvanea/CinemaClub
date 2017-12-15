package cinemaclub.guiMain.CustomerGui;

import cinemaclub.guiMain.GuiData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;

public class ModalBookedController {

    @FXML ImageView imageBox;
    @FXML Label titleText;
    @FXML Label descriptionText;
    @FXML Label timeText;
    @FXML Label runtimeText;
    @FXML Label dateText;
    @FXML Label seatText;

    /**
     * Gets all the booking information selected in GUI Data and displays this information.
     * Creates a string with the list of all the seats booked getting the size of the selected seats
     * array and using this to loop through all the seats in the array appending a comma and spacing to
     * all but the last seat
     */
    @FXML public void initialize() {
        titleText.setText(GuiData.getFilm().getTitle());
        descriptionText.setText(GuiData.getFilm().getDescription());
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
        seatText.setText(stringBuilder.toString());
        try {
            Image img = new Image(new FileInputStream("Images" + GuiData.getFilm().getImagePath()));
            imageBox.setImage(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the modal view.
     * @param actionEvent press the close button
     */
    public void pressClose(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
