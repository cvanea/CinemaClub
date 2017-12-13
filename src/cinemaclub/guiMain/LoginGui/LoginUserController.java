package cinemaclub.guiMain.LoginGui;

import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
import cinemaclub.user.Customer;
import exceptions.UserDetailsDoNotExistException;
import exceptions.UserDetailsIncorrectException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginUserController extends LoginMainController implements Initializable {

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Label errorLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String message = GuiData.getSuccessMessage();
        errorLabel.setText(message);
        if(message != null){
            errorLabel.setStyle("-fx-text-fill: green");
        }
    }

    public void pressButton(ActionEvent event) throws IOException {
        try {
            cinema.loginUser(username.getText(), password.getText());
            errorLabel.setText(cinema.getCurrentUser().IExist());
            if(cinema.getCurrentUser() instanceof Customer){
                GuiData.setViewTitle("Film Browser");
                StageSceneNavigator.customerStage(cinema);
            } else {
                GuiData.setViewTitle("Films");
                StageSceneNavigator.staffStage(cinema);
            }
            GuiData.setSuccessMessage("");
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (UserDetailsDoNotExistException | UserDetailsIncorrectException e) {
            System.out.println(e.getMessage());
            errorLabel.setText(e.getMessage());
        }
    }

    public void pressRegister(ActionEvent event) {
        StageSceneNavigator.loadLoginView(StageSceneNavigator.LOGIN_REGISTER);
    }

}
