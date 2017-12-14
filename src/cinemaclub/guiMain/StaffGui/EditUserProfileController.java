package cinemaclub.guiMain.StaffGui;

import cinemaclub.guiMain.GuiData;
import cinemaclub.user.Staff;
import cinemaclub.user.User;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import exceptions.NoSelectionMadeException;
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

    UserRow chosenUserRow;
    User chosenUser;


    /**
     * Initialises view filling the user table selected and selects the first line
     * Passes the selected user from the first index and fills the edit pane
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillUserTable();
        userTable.getSelectionModel().select(0);
        selectUser();
    }

    /**
     * Updates the selected user credentials in the databases. On update profile press
     * Checks whether the username text field has been altered. If not it ignores this
     * field.
     * Checks whether the user name exists in the databases. If it does an error message is thrown
     * Sets all other fields to the edited values.
     * @param event press update profile
     */
    public void pressSetProfileText(ActionEvent event) {
        try {
            if(!username.getText().equals(chosenUser.getUsername())) {
                cinema.setUsername(chosenUser, username.getText());
            }
            chosenUser.setEmail(email.getText());
            chosenUser.setPassword(password.getText());
            chosenUser.setFirstName(firstName.getText());
            chosenUser.setSurname(surname.getText());
            errorLabel.setText("Profile Updated");
            errorLabel.setStyle("-fx-text-fill: darkgreen");
            fillUserTable();
        } catch (UsernameTakenException e) {
            errorLabel.setText(e.getMessage());
            errorLabel.setStyle("-fx-text-fill: red");
        }
    }

    /**
     * Selects a user when a user is clicked in the user table
     * @param event click of user in user table
     */
    public void clickSelectUser(MouseEvent event) {
        selectUser();
    }

    /**
     * Fills the user table with list of all users and corresponding information
     * Clears the table. Creates an observable array list with Users
     * Sets the table information with user row information
     */
    private void fillUserTable() {
        userTable.getItems().clear();
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

    /**
     * Selects the user and sets the information in the table to the selected users credentials
     * 
     *
     */

    public void selectUser() {
        try {
            chosenUserRow = userTable.getSelectionModel().getSelectedItem();
            if (chosenUserRow == null) {
                throw new NoSelectionMadeException();
            }
            username.setText(chosenUserRow.getUsername());
            password.setText(chosenUserRow.getPassword());
            email.setText(chosenUserRow.getEmail());
            firstName.setText(chosenUserRow.getFirstName());
            surname.setText(chosenUserRow.getSurname());
            chosenUser = cinema.getUser(chosenUserRow.getUsername());
            errorLabel.setOpacity(0);
        } catch (NoSelectionMadeException e){
            errorLabel.setText(e.getMessage());
        }
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
