package cinemaclub.gui;

import cinemaclub.cinema.Cinema;
import javafx.event.ActionEvent;

public class AnchorCinema {

    Cinema cinema;

    public void setCinema(Cinema cinema) { // Setting the cinema-object
        this.cinema = cinema;
    }

    public void pressHome(ActionEvent event) {
//        System.out.println(cinema.getCurrentUser().IExist());
        Main.setPaneCinema(0);
    }

    public void pressProfile(ActionEvent event) {
        Main.setPaneCinema(1);
    }

    public void pressBookings(ActionEvent event) {
        Main.setPaneCinema(2);
    }

    public void pressFilmDisplay(ActionEvent event) {
//        Main.setPaneCinema(1);
    }

    public void pressFilmEdit(ActionEvent event) {
//        Main.setPaneCinema(1);
    }

}
