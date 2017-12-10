package cinemaclub.guiMain.StaffGui;

import cinemaclub.guiMain.CustomerGui.CustomerMainController;
import cinemaclub.guiMain.StageSceneNavigator;
import cinemaclub.user.Booking;
import exceptions.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StaffProfileController extends StaffMainController implements Initializable {

    @FXML Label nameBox;
    @FXML Label emailBox;
    @FXML Label passwordBox;
    @FXML Label firstNameBox;
    @FXML Label lastNameBox;

    @FXML public void initialize(URL location, ResourceBundle resources) {
        nameBox.setText(cinema.getProfileDetails().getUsername());
        emailBox.setText(cinema.getCurrentUser().getEmail());
        passwordBox.setText(cinema.getCurrentUser().getPassword());
        firstNameBox.setText(cinema.getProfileDetails().getFirstName());
        lastNameBox.setText(cinema.getProfileDetails().getSurname());
    }

    public void setProfileText(ActionEvent event) {
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_PROFILE_EDIT);
    }




}
