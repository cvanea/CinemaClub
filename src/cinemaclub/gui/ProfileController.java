package cinemaclub.gui;

import cinemaclub.cinema.Cinema;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfileController extends AnchorCinema{

    @FXML Label nameBox;
    @FXML Label emailBox;
    @FXML Label passwordBox;
//    @FXML Label staffIDBox;

//    public void setNameBox(){
//        nameBox.setText(cinema.getCurrentUser().getName());
//    }


   @FXML private void initialize() {
        nameBox.setText(cinema.getCurrentUser().getName());
        emailBox.setText(cinema.getCurrentUser().getEmail());
        passwordBox.setText(cinema.getCurrentUser().getPassword());
        //cinema.getProfileDetails.getUserName
       }

    public void pressEdit(ActionEvent event) {
        Main.setPaneCinema(3);
    }




}
