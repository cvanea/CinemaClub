package cinemaclub.guiMain.CustomerGui;

import cinemaclub.guiMain.StageSceneNavigator;
import exceptions.UsernameTakenException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerProfileEditController extends CustomerMainController implements Initializable{

    @FXML TextField nameBoxEdit;
    @FXML TextField emailBoxEdit;
    @FXML TextField passwordBoxEdit;
    @FXML TextField firstNameBoxEdit;
    @FXML TextField lastNameBoxEdit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       nameBoxEdit.setText(cinema.getProfileDetails().getUsername());
       emailBoxEdit.setText(cinema.getProfileDetails().getEmail());
       passwordBoxEdit.setText(cinema.getProfileDetails().getPassword());
       firstNameBoxEdit.setText(cinema.getProfileDetails().getFirstName());
       lastNameBoxEdit.setText(cinema.getProfileDetails().getSurname());
       }

    public void pressSubmitEdit(ActionEvent event) throws IOException {
        try {
            if(!nameBoxEdit.getText().equals(cinema.getProfileDetails().getUsername())) {
                cinema.setUsername(nameBoxEdit.getText());
            }
            cinema.setUserEmail(emailBoxEdit.getText());
            cinema.setUserPassword(passwordBoxEdit.getText());
            cinema.setUserFirstName(firstNameBoxEdit.getText());
            cinema.setUserSurname(lastNameBoxEdit.getText());
            StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_PROFILE);
        } catch (UsernameTakenException e) {
            System.out.println(e.getMessage());
        }
    }
    public void pressCancel(ActionEvent event) throws IOException {
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_PROFILE);
    }



}
