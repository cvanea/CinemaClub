package cinemaclub.guiMain.LoginGui;

import javafx.event.ActionEvent;
import javafx.scene.Node;

public class ModalRegisterController {

    /**
     * Button that closes the modal view.
     * @param actionEvent press of the close button
     */
    public void pressClose(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
