package cinemaclub.guiMain.StaffGui;

import cinemaclub.user.User;
import exceptions.UsernameTakenException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditUserProfileController extends MainController implements Initializable {

    @FXML TextField username;
    @FXML TextField password;
    @FXML TextField email;
    @FXML TextField firstName;
    @FXML TextField surname;
    @FXML Label errorLabel;
    @FXML TableView<User> userTable;
    @FXML TableColumn <User, String> usernameCol;
    @FXML TableColumn <User, String> emailCol;
    @FXML TableColumn <User, String> firstNameCol;
    @FXML TableColumn <User, String> surnameCol;

    private User chosenUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillUserTable();
        errorLabel.setText("Please choose a User to edit.");
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

    public void selectUser(MouseEvent event) {
        chosenUser = userTable.getSelectionModel().getSelectedItem();
        username.setText(chosenUser.getUsername());
        password.setText(chosenUser.getPassword());
        email.setText(chosenUser.getEmail());
        firstName.setText(chosenUser.getFirstName());
        surname.setText(chosenUser.getSurname());
    }

    private void fillUserTable() {
        ObservableList<User> data = FXCollections.observableArrayList();
        ArrayList<User> allUsers = cinema.getAllUsers();
        data.addAll(allUsers);
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        userTable.setItems(data);
    }
}
