package cinemaclub.cinema;

import java.io.Serializable;
import java.util.ArrayList;

public class Showing implements Serializable {

    String date;
    String time;
    Film film;
    ArrayList<String> takenSeats;

    Showing(String date, String time, Film film, ArrayList<String> takenSeats) {
        this.date = date;
        this.time = time;
        this.film = film;
        this.takenSeats = takenSeats;
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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public ArrayList<String> getTakenSeats() {
        return takenSeats;
    }

    public void setTakenSeats(ArrayList<String> takenSeats) {
        this.takenSeats = takenSeats;
    }
}
