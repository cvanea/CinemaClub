package cinemaclub.guiMain.StaffGui;

import cinemaclub.user.Staff;
import cinemaclub.user.User;
import exceptions.UsernameTakenException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class ProfileController extends MainController implements Initializable {

    @FXML TextField username;
    @FXML TextField password;
    @FXML TextField email;
    @FXML TextField firstName;
    @FXML TextField surname;
    @FXML Label errorLabel;
    @FXML Button addStaffId;
    @FXML TableView<Staff> staffTable;
    @FXML TableColumn <Staff, String> usernameCol;
    @FXML TableColumn <Staff, String> firstNameCol;
    @FXML TableColumn <Staff, String> surnameCol;
    @FXML TableColumn <Staff, String> staffIdCol;
    @FXML TableView<staffIdRow> staffIdTable;
    @FXML TableColumn <staffIdRow, String> staffIdCol2;
    @FXML TableColumn <staffIdRow, String> usernameCol2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(cinema.getProfileDetails().getUsername());
        email.setText(cinema.getCurrentUser().getEmail());
        password.setText(cinema.getCurrentUser().getPassword());
        firstName.setText(cinema.getProfileDetails().getFirstName());
        surname.setText(cinema.getProfileDetails().getSurname());

        fillStaffTable();
        fillStaffIdTable();
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
            staffIdTable.getItems().clear();
            fillStaffIdTable();
        } catch (UsernameTakenException e) {
            errorLabel.setText(e.getMessage());
            errorLabel.setStyle("-fx-text-fill: red");
        }
    }

    private void fillStaffTable() {
        ObservableList<Staff> data = FXCollections.observableArrayList();
        ArrayList<User> allUsers = cinema.getAllUsers();

        for (User user : allUsers) {
            if (user instanceof Staff) {
                Staff staff = (Staff) user;
                data.add(staff);
            }
        }

        usernameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        staffIdCol.setCellValueFactory(new PropertyValueFactory<>("StaffId"));
        staffTable.setItems(data);
    }

    private void fillStaffIdTable() {
        ObservableList<staffIdRow> data = FXCollections.observableArrayList();
        Map<String, String> allStaffIds = cinema.getStaffIDs();

        for (Map.Entry entry : allStaffIds.entrySet()) {
            data.add(new staffIdRow(entry));
        }

        usernameCol2.setCellValueFactory(new PropertyValueFactory<>("Username"));
        staffIdCol2.setCellValueFactory(new PropertyValueFactory<>("StaffId"));
        staffIdTable.setItems(data);
    }

    public class staffIdRow {
        private String staffId;
        private String username;

        private staffIdRow(Map.Entry staffIdPair) {
            this.staffId = staffIdPair.getKey().toString();
            this.username = staffIdPair.getValue().toString();
        }

        public String getStaffId() {
            return staffId;
        }

        public String getUsername() {
            return username;
        }
    }
}
