package cinemaclub.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfileController extends AnchorCinema{

    @FXML Label nameBox;
    @FXML Label emailBox;
    @FXML Label passwordBox;

   @FXML private void initialize() {
        nameBox.setText(cinema.getCurrentUser().getUsername());
        emailBox.setText(cinema.getCurrentUser().getEmail());
        passwordBox.setText(cinema.getCurrentUser().getPassword());
        //cinema.getProfileDetails.getUserName
       }

    public void reload() {
        System.out.println("I should be reloading");
        nameBox.setText(cinema.getProfileDetails().getUsername());
        System.out.println(cinema.getProfileDetails().getUsername());
        emailBox.setText(cinema.getCurrentUser().getEmail());
        passwordBox.setText(cinema.getCurrentUser().getPassword());
        //cinema.getProfileDetails.getUserName
    }


    public void pressReload(ActionEvent event) {
        reload();
    }



    public void pressEdit(ActionEvent event) {
        Main.setPaneCinema(3);
    }




}
