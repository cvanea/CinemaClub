package cinemaclub.guiMain.LoginGui;

import cinemaclub.cinema.Cinema;
import cinemaclub.guiMain.StageSceneNavigator;
import exceptions.UserDetailsDoNotExistException;
import exceptions.UserDetailsIncorrectException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginUserController {

    private static Cinema cinema = new Cinema();

    public static Cinema getCinema() {
        return cinema;
    }

    @FXML
    private TextField username;
    @FXML private TextField password;
    @FXML private TextField email;
    @FXML private Label errorLabel;

    public void pressButton(ActionEvent event) throws IOException {
        try {
            cinema.loginUser(username.getText(), password.getText());
            System.out.println(cinema.getCurrentUser().IExist());
            errorLabel.setText(cinema.getCurrentUser().IExist());
            cinema.getCurrentUser();
            StageSceneNavigator.customerStage(cinema);
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (UserDetailsDoNotExistException | UserDetailsIncorrectException e) {
            System.out.println(e.getMessage());
            errorLabel.setText(e.getMessage());
        }
    }

    public void pressRegister(ActionEvent event) {
//        Main.setPaneLogin(1);
        StageSceneNavigator.loadLoginView(StageSceneNavigator.LOGIN_REGISTER);

    }

    public void newStage(ActionEvent event) throws IOException {
        StageSceneNavigator.customerStage(cinema);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}