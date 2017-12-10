package cinemaclub.guiMain.LoginGui;

import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
import cinemaclub.user.Customer;
import exceptions.UserDetailsDoNotExistException;
import exceptions.UserDetailsIncorrectException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginUserController extends LoginMainController {

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label errorLabel;

    @FXML private void initialize() {
        errorLabel.setText(GuiData.getSuccessMessage());
    }

    public void pressButton(ActionEvent event) throws IOException {
        try {
            cinema.loginUser(username.getText(), password.getText());
            errorLabel.setText(cinema.getCurrentUser().IExist());
            if(cinema.getCurrentUser() instanceof Customer){
                StageSceneNavigator.customerStage(cinema);
            } else {
                StageSceneNavigator.staffStage(cinema);
            }
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (UserDetailsDoNotExistException | UserDetailsIncorrectException e) {
            System.out.println(e.getMessage());
            errorLabel.setText(e.getMessage());
        }
    }

    public void pressRegister(ActionEvent event) {
        StageSceneNavigator.loadLoginView(StageSceneNavigator.LOGIN_REGISTER);
    }

    public void newStage(ActionEvent event) throws IOException {
        StageSceneNavigator.customerStage(cinema);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
