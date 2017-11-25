package cinemaclub.gui;

import cinemaclub.cinema.Cinema;

import javafx.event.ActionEvent;

public class Controller {

    Cinema cinema = new Cinema();


    public void pressButton(ActionEvent event) {
        System.out.println("Button pressed");
    }

}
