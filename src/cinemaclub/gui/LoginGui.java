package cinemaclub.gui;

import cinemaclub.cinema.Cinema;

import exceptions.UserDetailsDoNotExistException;
import exceptions.UserDetailsIncorrectException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class LoginGui {

    private static Cinema cinema = new Cinema();

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField email;
    @FXML private Label errorLabel;

    public static Cinema getCinema() {
        return cinema;
    }

    public void pressButton(ActionEvent event) {
        try {
            cinema.loginUser(username.getText(), email.getText(), password.getText());
            errorLabel.setText(cinema.getCurrentUser().IExist());
//            cinema.getCurrentUser();
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
