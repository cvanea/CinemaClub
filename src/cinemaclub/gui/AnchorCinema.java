package cinemaclub.gui;

import cinemaclub.cinema.Cinema;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AnchorCinema {

    @FXML Label editText;
    static Cinema cinema = LoginGui.getCinema();

    public void pressHome(ActionEvent event) {
        System.out.println(cinema.getCurrentUser().IExist());
        Main.setPaneCinema(0);
    }

    public void pressProfile(ActionEvent event) {
        Main.setPaneCinema(1);
    }

    public void pressBookings(ActionEvent event) {
        Main.setPaneCinema(2);
    }

    public void pressFilmDisplay(ActionEvent event) {
//        MainTester.setPaneCinema(1);
    }

    public void pressFilmEdit(ActionEvent event) {
//        MainTester.setPaneCinema(1);
    }



}
