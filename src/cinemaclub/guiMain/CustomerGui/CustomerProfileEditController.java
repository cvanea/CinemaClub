package cinemaclub.guiMain.CustomerGui;

import cinemaclub.gui.AnchorCinema;
import cinemaclub.gui.Main;
import cinemaclub.gui.ProfileController;
import cinemaclub.guiMain.StageSceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CustomerProfileEditController extends CustomerMainController{

    @FXML TextField nameBoxEdit;
    @FXML TextField emailBoxEdit;
    @FXML TextField passwordBoxEdit;

    private ProfileController profileController;

    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }


   @FXML private void initialize() {
       nameBoxEdit.setText(cinema.getProfileDetails().getUserName());
       emailBoxEdit.setText(cinema.getProfileDetails().getEmail());
       passwordBoxEdit.setText(cinema.getProfileDetails().getPassword());
       }

    public void pressSubmitEdit(ActionEvent event) throws IOException {
        cinema.setUsername(nameBoxEdit.getText());
        cinema.setUserEmail(emailBoxEdit.getText());
        cinema.setUserPassword(passwordBoxEdit.getText());
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_PROFILE);
    }
    public void pressCancel(ActionEvent event) throws IOException {
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_PROFILE);
    }



}
