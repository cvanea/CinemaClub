package cinemaclub.guiMain.LoginGui;

import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
import exceptions.*;
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

    /**
     * Button that checks registration details and registers user
     * Gets all the details in the text fields and passes these to
     * register new user
     * passes success message to GuiData
     * Changes view to login screen
     * @param event press of register button
     */
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
        } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException | EmptyUserInputException | NotValidEmailException e) {
            errorLabel.setText(e.getMessage());
        }
    }


    /**
     * Returns the user to the login pane
     * sets the success message to blank (viewed on the login page)
     * @param event  press return button
     */
    public void returnButton(ActionEvent event) {
        GuiData.setSuccessMessage("");
        StageSceneNavigator.loadLoginView(StageSceneNavigator.LOGIN_USER);
    }


    /**
     * Sets the opactiy of the staff checkbox
     *
     * @param event  press return button
     */
    public void tickCheckBox(ActionEvent event) {

        if (idOpacity == 0) {
            idOpacity = 1;
        } else {
            idOpacity = 0;
        }
        staffID.setOpacity(idOpacity);
    }
}
