package cinemaclub.guiMain.LoginGui;

import cinemaclub.guiMain.GuiData;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;


public class ModalRegisterController {

    /**
     * Button that closes the modal view.
     * @param actionEvent press of the close button
     */
    public void pressClose(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
