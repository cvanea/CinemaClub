package cinemaclub.user;

import cinemaclub.cinema.Screen;

import java.io.Serializable;
import java.util.Map;

public class Booking implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private String filmTitle;
    private Map<String, Integer> dateTime;
    private Screen screen;
    private String seat;

    public Booking(String filmTitle, Map<String, Integer> dateTime, Screen screen, String seat) {
        this.filmTitle = filmTitle;
        this.dateTime = dateTime;
        this.screen = screen;
        this.seat = seat;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public Map<String, Integer> getDateTime() {
        return dateTime;
    }

    public void setDateTime(Map<String, Integer> dateTime) {
        this.dateTime = dateTime;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
