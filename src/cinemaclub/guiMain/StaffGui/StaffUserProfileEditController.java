package cinemaclub.guiMain.StaffGui;

import cinemaclub.user.Booking;
import exceptions.UsernameTakenException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffUserProfileEditController extends StaffMainController implements Initializable {

    @FXML TextField username;
    @FXML TextField password;
    @FXML TextField email;
    @FXML TextField firstName;
    @FXML TextField surname;
    @FXML Label errorLabel;
    @FXML TableView<Booking> futureTable;
    @FXML TableColumn <Booking, String> filmTable;
    @FXML TableColumn <Booking, String> dateTable;
    @FXML TableColumn <Booking, String> timeTable;
    @FXML TableColumn <Booking, String> seatTable;
    @FXML TableView<Booking> pastTable;
    @FXML TableColumn <Booking, String> filmTableP;
    @FXML TableColumn <Booking, String> dateTableP;
    @FXML TableColumn <Booking, String> timeTableP;
    @FXML TableColumn <Booking, String> seatTableP;

    private Booking chosenBooking;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        username.setText(cinema.getProfileDetails().getUsername());
//        email.setText(cinema.getCurrentUser().getEmail());
//        password.setText(cinema.getCurrentUser().getPassword());
//        firstName.setText(cinema.getProfileDetails().getFirstName());
//        surname.setText(cinema.getProfileDetails().getSurname());
    }

    public void setProfileText(ActionEvent event) {
        try {
            if(!username.getText().equals(cinema.getProfileDetails().getUsername())) {
                cinema.setUsername(username.getText());
            }
            cinema.setUserEmail(email.getText());
            cinema.setUserPassword(password.getText());
            cinema.setUserFirstName(firstName.getText());
            cinema.setUserSurname(surname.getText());
            errorLabel.setText("Profile Updated");
            errorLabel.setStyle("-fx-text-fill: darkgreen");
        } catch (UsernameTakenException e) {
            errorLabel.setText(e.getMessage());
            errorLabel.setStyle("-fx-text-fill: red");
        }
    }
}
