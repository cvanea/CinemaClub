package cinemaclub.user;

import cinemaclub.cinema.Screen;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Booking implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private String filmTitle;
    private LocalDateTime dateTime;
    private Screen screen;
    private String seat;

    public Booking(String filmTitle, LocalDateTime dateTime,Screen screen, String seat) {
        this.filmTitle = filmTitle;
        this.dateTime = dateTime;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {

        return this.getFilmTitle() + " " + this.getDateTime();
    }
}
