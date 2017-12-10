package cinemaclub.gui;

import cinemaclub.cinema.Cinema;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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
        FXMLLoader loader2 = new FXMLLoader(Main.class.getResource("ProfileGUI.fxml"));
        ProfileController mainController2 = loader2.getController(); // This did the "trick"
//        mainController2.setCinema(cinema);
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
