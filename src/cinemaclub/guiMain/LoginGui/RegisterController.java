package cinemaclub.guiMain.LoginGui;

import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
import exceptions.EmptyUserInputException;
import exceptions.IncorrectStaffIDException;
import exceptions.StaffIDTakenException;
import exceptions.UsernameTakenException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterController extends LoginMainController {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField firstName;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private TextField staffID;
    @FXML
    private CheckBox staffCheck;
    @FXML
    private Label errorLabel;

    private int idOpacity = 0;

    @FXML
    public void initialize() {
        staffID.setOpacity(idOpacity);
    }

    public void registerButton(ActionEvent event) {
        try {
            if (staffCheck.isSelected()) {
                cinema.registerUser(username.getText(), email.getText(), password.getText(), firstName.getText(), surname.getText(), "staff", staffID.getText());
            } else {
                cinema.registerUser(username.getText(), email.getText(), password.getText(), firstName.getText(), surname.getText(), "customer", null);
            }
            errorLabel.setText("Registered");
            GuiData.setSuccessMessage("Successfully Registered: " + username.getText());
            StageSceneNavigator.loadLoginView(StageSceneNavigator.LOGIN_USER);
        } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException | EmptyUserInputException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void loginButton(ActionEvent event) {
        GuiData.setSuccessMessage("");
        StageSceneNavigator.loadLoginView(StageSceneNavigator.LOGIN_USER);
    }

    public void tickCheckBox(ActionEvent event) {

        if (idOpacity == 0) {
            idOpacity = 1;
        } else {
            idOpacity = 0;
        }
        staffID.setOpacity(idOpacity);
    }
}
