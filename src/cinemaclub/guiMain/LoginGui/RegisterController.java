package cinemaclub.guiMain.LoginGui;

import cinemaclub.cinema.Cinema;
import cinemaclub.guiMain.StageSceneNavigator;
import exceptions.IncorrectStaffIDException;
import exceptions.StaffIDTakenException;
import exceptions.UsernameTakenException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController extends LoginMainController {
    private Cinema cinema = new Cinema();

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField firstName;
    @FXML private TextField surname;
    @FXML private TextField email;
    @FXML private TextField staffID;
    @FXML private CheckBox staffCheck;
    @FXML private Label errorLabel;

    private String successMessage;

    public String getSuccessMessage() {
        return successMessage;
    }

    public void registerButton(ActionEvent event) {

        try {
            if (staffCheck.isSelected()) {
                cinema.registerUser(username.getText(), email.getText(), password.getText(), firstName.getText(), surname.getText(),"staff", staffID.getText());
            } else {
                cinema.registerUser(username.getText(), email.getText(), password.getText(), firstName.getText(),surname.getText(), "customer", null);
            }
            successMessage = "Success!";
            errorLabel.setText("Registered");
            StageSceneNavigator.loadLoginView(StageSceneNavigator.LOGIN_USER);
        } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException e) {
            errorLabel.setText(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void loginButton(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(RegisterController.class.getResource("ModalRegister.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("My modal window");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node)event.getSource()).getScene().getWindow() );
        stage.show();
        StageSceneNavigator.loadLoginView(StageSceneNavigator.LOGIN_USER);
    }

}
