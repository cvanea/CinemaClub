package cinemaclub.user;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Screen;
import cinemaclub.cinema.Showing;

import java.io.Serializable;

public class Booking implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private Showing showing;
    private String seat;

    public Booking(Showing showing, String seat) {
        this.showing = showing;
        this.seat = seat;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Screen getScreen() {
        return showing.getScreen();
    }

    public String getDate() {
        return showing.getDate();
    }

    public String getTime() {
        return showing.getTime();
    }

    public Film getFilm() {
        return showing.getFilm();
    }

    public String getTitle() { return  showing.getFilm().getTitle();}

    @Override
    public String toString() {
        return "Booking{" +
            "showing=" + showing +
            ", seat='" + seat + '\'' +
            '}';
    }
}
