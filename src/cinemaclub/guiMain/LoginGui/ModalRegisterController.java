package cinemaclub.guiMain.LoginGui;

import javafx.event.ActionEvent;
import javafx.scene.Node;



public class ModalRegisterController {

    public void pressClose(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
