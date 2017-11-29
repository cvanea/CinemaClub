package cinemaclub.gui;

import cinemaclub.cinema.Cinema;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfileController extends AnchorCinema{

    @FXML Label nameBox;
    @FXML Label emailBox;
    @FXML Label passwordBox;
    @FXML Label staffIDBox;

    public void setProfileText(ActionEvent event) {
//        nameBox.setText(cinema.getCurrentUser().getName());
        System.out.println(cinema.getCurrentUser().IExist());
    }


}
