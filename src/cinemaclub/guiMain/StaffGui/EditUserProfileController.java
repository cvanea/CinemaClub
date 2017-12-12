package cinemaclub.guiMain.StaffGui;

import cinemaclub.user.Staff;
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
    @FXML TableView<UserRow> userTable;
    @FXML TableColumn <UserRow, String> usernameCol;
    @FXML TableColumn <UserRow, String> emailCol;
    @FXML TableColumn <UserRow, String> firstNameCol;
    @FXML TableColumn <UserRow, String> surnameCol;
    @FXML TableColumn <UserRow, String> staffIdCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillUserTable();
        errorLabel.setText("Please choose a user to edit.");
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
        UserRow chosenUser = userTable.getSelectionModel().getSelectedItem();
        username.setText(chosenUser.getUsername());
        password.setText(chosenUser.getPassword());
        email.setText(chosenUser.getEmail());
        firstName.setText(chosenUser.getFirstName());
        surname.setText(chosenUser.getSurname());

        errorLabel.setOpacity(0);
    }

    private void fillUserTable() {
        ObservableList<UserRow> data = FXCollections.observableArrayList();
        ArrayList<User> allUsers = cinema.getAllUsers();

        for (User user : allUsers) {
            data.add(new UserRow(user));
        }

        usernameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        staffIdCol.setCellValueFactory(new PropertyValueFactory<>("StaffId"));
        userTable.setItems(data);
    }

    public class UserRow {
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String surname;
        private String staffId;

        private UserRow(User user) {
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.email = user.getEmail();
            this.firstName = user.getFirstName();
            this.surname = user.getSurname();

            if (user instanceof Staff) {
                this.staffId = cinema.getStaffIdByUsername(username);
            } else {
                this.staffId = "";
            }
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getSurname() {
            return surname;
        }

        public String getStaffId() {
            return staffId;
        }
    }
}
