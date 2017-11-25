package cinemaclub.gui;

import cinemaclub.cinema.Cinema;

import exceptions.UserDetailsDoNotExistException;
import exceptions.UserDetailsIncorrectException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {

    private Cinema cinema = new Cinema();

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField email;

    public void pressButton(ActionEvent event) {

        try {
            cinema.loginUser(username.getText(), email.getText(), password.getText());
            System.out.println(cinema.getCurrentUser().IExist());
        } catch (UserDetailsDoNotExistException | UserDetailsIncorrectException e) {
            System.out.println(e.getMessage());
        }
    }

}
