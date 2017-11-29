package cinemaclub.gui;

import cinemaclub.cinema.Cinema;

import exceptions.UserDetailsDoNotExistException;
import exceptions.UserDetailsIncorrectException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginGui {

    private Cinema cinema = new Cinema();

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField email;
    @FXML private Label errorLabel;

    public void pressButton(ActionEvent event) {
        try {
            cinema.loginUser(username.getText(), email.getText(), password.getText());
            System.out.println(cinema.getCurrentUser().IExist());
            errorLabel.setText(cinema.getCurrentUser().IExist());
            cinema.getCurrentUser();
            Main.cinemaStage(); // Go To the Bookings Screen
        } catch (UserDetailsDoNotExistException | UserDetailsIncorrectException e) {
            System.out.println(e.getMessage());
            errorLabel.setText(e.getMessage());
        }
    }

    public void pressRegister(ActionEvent event) {
        Main.setPaneLogin(1);
    }

    public void newStage(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Main.cinemaStage();
    }

}
