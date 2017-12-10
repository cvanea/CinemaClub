package cinemaclub.guiMain.LoginGui;

import cinemaclub.guiMain.GuiData;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;


public class ModalRegisterController implements Initializable {

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void pressClose(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
