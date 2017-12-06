package cinemaclub.guiMain.CustomerGui;

import cinemaclub.guiMain.StageSceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CustomerProfileController extends CustomerMainController {

    @FXML Label nameBox;
    @FXML Label emailBox;
    @FXML Label passwordBox;
    @FXML Label firstNameBox;
    @FXML Label lastNameBox;
    @FXML ListView futureList;
    @FXML ListView pastList;

    @FXML private void initialize() {
        nameBox.setText(cinema.getProfileDetails().getUsername());
        emailBox.setText(cinema.getCurrentUser().getEmail());
        passwordBox.setText(cinema.getCurrentUser().getPassword());
        firstNameBox.setText(cinema.getProfileDetails().getFirstName());
        lastNameBox.setText(cinema.getProfileDetails().getSurname());
    }

    public void cancelBooking(ActionEvent event){

    }

    public void futureMouseClick(ActionEvent event){

    }

    public void setProfileText(ActionEvent event) {
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_PROFILE_EDIT);
    }

}
