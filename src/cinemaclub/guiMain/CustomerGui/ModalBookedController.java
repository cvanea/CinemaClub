package cinemaclub.guiMain.CustomerGui;

import javafx.event.ActionEvent;
import javafx.scene.Node;


public class ModalBookedController {

    public void pressClose(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
