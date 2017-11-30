package cinemaclub.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ProfileEditController extends AnchorCinema{

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
//        profileController.nameBox.setText("hello");
//        profileController.reload();
        Main.setPaneCinema(1);
    }

    public void reloadController() throws IOException{
//        // getting the controller class and execute the reload method
//
//
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("ProfileGui.fxml"));
//        loader.load();
//        ProfileController profileController = loader.getController();
//        profileController.reload();
    }


}
