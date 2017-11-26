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

    @FXML
    private TextField username;
    @FXML private TextField password;
    @FXML private TextField email;
    @FXML private TextField staffID;
    @FXML private CheckBox staffCheck;
    @FXML private Label errorLabel;


    public void registerButton(ActionEvent event) {

        try {
            if (staffCheck.equals(true)) {
                cinema.registerUser(username.getText(), email.getText(), password.getText(), "staff", staffID.getText());

            } else {
            cinema.registerUser(username.getText(), email.getText(), password.getText(), "customer", null);
            }
            errorLabel.setText("Registered");

        } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException e) {
            errorLabel.setText(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void loginButton(ActionEvent event) {
        Main.set_pane(0);
    }

    public void checkBox(ActionEvent event) {
//        errorLabel.setText("Checked");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
