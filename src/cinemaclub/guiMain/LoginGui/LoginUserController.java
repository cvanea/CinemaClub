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

/**
 * Controller for login view.
 */
public class LoginUserController extends LoginMainController implements Initializable {

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Label errorLabel;

    /**
     * Initialises the login views checks message if registered user.
     * @param location - The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources - used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String message = GuiData.getSuccessMessage();
        errorLabel.setText(message);
        if(message != null){
            errorLabel.setStyle("-fx-text-fill: green");
        }
    }

    /**
    * Button that allows the user to log in to the view.
     * checks to see whether the user credentials are in the
     * database otherwise updates error label with message
     * @param event login button press
     * @throws IOException error message
     */
    public void pressButton(ActionEvent event) throws IOException {
        try {
            cinema.loginUser(username.getText(), password.getText());
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
            errorLabel.setText(e.getMessage());
        }
    }

    /**
     * Button that switches the view to register pane.
     * @param event register button press
     */
    public void pressRegister(ActionEvent event) {
        StageSceneNavigator.loadLoginView(StageSceneNavigator.LOGIN_REGISTER);
    }
}
