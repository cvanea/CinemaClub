package cinemaclub.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProfileEditController extends AnchorCinema{

    @FXML TextField nameBoxEdit;
    @FXML TextField emailBoxEdit;
    @FXML TextField passwordBoxEdit;

   @FXML private void initialize() {
       nameBoxEdit.setText(cinema.getCurrentUser().getName());
       emailBoxEdit.setText(cinema.getCurrentUser().getEmail());
       passwordBoxEdit.setText(cinema.getCurrentUser().getPassword());
       }

    public void pressSubmitEdit(ActionEvent event) {
       // TODO: Add setters for profile
        Main.setPaneCinema(1);
    }


}
