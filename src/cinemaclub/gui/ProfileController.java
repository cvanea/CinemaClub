package cinemaclub.gui;

import cinemaclub.cinema.Cinema;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController extends AnchorCinema{

    @FXML Label nameBox;
    @FXML Label emailBox;
    @FXML Label passwordBox;

   @FXML private void initialize() {
        nameBox.setText(cinema.getCurrentUser().getName());
        emailBox.setText(cinema.getCurrentUser().getEmail());
        passwordBox.setText(cinema.getCurrentUser().getPassword());
        //cinema.getProfileDetails.getUserName
       }

    public void reload() {
        System.out.println("I should be reloading");
        nameBox.setText(cinema.getProfileDetails().getUserName());
        System.out.println(cinema.getProfileDetails().getUserName());
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
