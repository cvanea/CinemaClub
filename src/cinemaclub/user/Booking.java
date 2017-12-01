package cinemaclub.user;

import java.time.LocalDateTime;

public class Booking {

    String filmTitle;
    LocalDateTime dateTime;

    public Booking(String filmTitle, LocalDateTime dateTime) {
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

        return "{" + this.getFilmTitle() + " " + this.getDateTime() + "}";
    }
}
