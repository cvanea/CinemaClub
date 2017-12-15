package cinemaclub.guiMain.StaffGui;

import cinemaclub.user.Staff;
import cinemaclub.user.User;
import exceptions.StaffIdNotEmptyException;
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
    @FXML Label errorLabel2;
    @FXML Button addStaffId;
    @FXML TableView<Staff> staffTable;
    @FXML TableColumn <Staff, String> usernameCol;
    @FXML TableColumn <Staff, String> firstNameCol;
    @FXML TableColumn <Staff, String> surnameCol;
    @FXML TableColumn <Staff, String> staffIdCol;
    @FXML TableView<staffIdRow> staffIdTable;
    @FXML TableColumn <staffIdRow, String> staffIdCol2;
    @FXML TableColumn <staffIdRow, String> usernameCol2;

    /**
     * Sets the profile pane with the staff members details.
     * Fills the staff id and staff tables.
     * @param location - The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources - used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(cinema.getProfileDetails().getUsername());
        email.setText(cinema.getProfileDetails().getEmail());
        password.setText(cinema.getProfileDetails().getPassword());
        firstName.setText(cinema.getProfileDetails().getFirstName());
        surname.setText(cinema.getProfileDetails().getSurname());
        fillStaffTable();
        fillStaffIdTable();
    }

    /**
     * Updates the staff users profile in the database.
     * Updates staff info table
     * Makes sure username is not already taken if changed
     * @param event press update.
     */
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

            staffTable.getItems().clear();
            fillStaffTable();

            staffIdTable.getItems().clear();
            fillStaffIdTable();
        } catch (UsernameTakenException e) {
            errorLabel.setText(e.getMessage());
            errorLabel.setStyle("-fx-text-fill: red");
        }
    }

    /**
     * Adds staff ID number to the databases.
     * Looks for last ID number and add 1.
     * @param event press add staff ID
     */
    public void pressAddStaffId(ActionEvent event) {
        int numberOfStaffIds = staffIdTable.getItems().size();
        int newStaffId = numberOfStaffIds + 1;
        String newStaffIdString = Integer.toString(newStaffId);
        cinema.addStaffID(newStaffIdString, "noStaff");

        staffIdTable.getItems().clear();
        fillStaffIdTable();

        errorLabel2.setText("Added new Staff ID");
        errorLabel2.setStyle("-fx-text-fill: darkgreen");
    }

    /**
     * Remove staff ID number from database.
     * @param event press remove staff ID
     */
    public void pressRemoveStaffId(ActionEvent event) {
        String selectedStaffId = staffIdTable.getSelectionModel().getSelectedItem().getStaffId();

        try {
            cinema.deleteStaffId(selectedStaffId);

            staffIdTable.getItems().clear();
            fillStaffIdTable();
        } catch (StaffIdNotEmptyException e) {
            errorLabel2.setText(e.getMessage());
        }
    }

    /**
     * Fills the staff user table with information.
     */
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

    /**
     * Fills the staff ID table
     */
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

    /**
     * Helper class to create the staff ID Row for the table.
     */
    public class staffIdRow {
        private String staffId;
        private String username;

        /**
         * Creates a Username Staff ID hash map.
         * @param staffIdPair Staff ID hashmap
         */
        private staffIdRow(Map.Entry staffIdPair) {
            this.staffId = staffIdPair.getKey().toString();
            this.username = staffIdPair.getValue().toString();
        }

        /**
         * Gets the staff id.
         * @return staff ID string
         */
        public String getStaffId() {
            return staffId;
        }

        /**
         * Gets the username.
         * @return staff username string
         */
        public String getUsername() {
            return username;
        }
    }


}
