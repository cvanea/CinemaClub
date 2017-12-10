package cinemaclub.gui;

import cinemaclub.cinema.Cinema;
import exceptions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterGui implements Initializable {

    private Cinema cinema = new Cinema();

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField email;
    @FXML private TextField staffID;
    @FXML private CheckBox staffCheck;
    @FXML private Label errorLabel;


    public void registerButton(ActionEvent event) {

        try {
            if (staffCheck.isSelected()) {
                cinema.registerUser(username.getText(), email.getText(), password.getText(), "fix", "fix", "staff", staffID.getText());

            } else {
            cinema.registerUser(username.getText(), email.getText(), password.getText(), "fix", "fix", "customer", null);
            }
            errorLabel.setText("Registered");
            Main.setPaneLogin(0);

        } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException | EmptyUserInputException e) {
            errorLabel.setText(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void loginButton(ActionEvent event) {
        Main.setPaneLogin(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
