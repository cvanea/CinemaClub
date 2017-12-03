package cinemaclub.user;

import java.io.Serializable;

public class Booking implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private String filmTitle;
    private String date;
    private String time;
    private Integer screenNumber;
    private String seat;

    public Booking(String filmTitle, String date, String time, Integer screenNumber, String seat) {
        this.filmTitle = filmTitle;
        this.date = date;
        this.time = time;
        this.screenNumber = screenNumber;
        this.seat = seat;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer screenNumber() {
        return screenNumber;
    }

    public void setScreen(Integer screenNumber) {
        this.screenNumber = screenNumber;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Booking{" +
            "filmTitle='" + filmTitle + '\'' + ", date='" + date + '\'' + ", time='" + time + '\'' +
            ", screenNumber=" + screenNumber + ", seat='" + seat + '\'' + '}';
    }
}
