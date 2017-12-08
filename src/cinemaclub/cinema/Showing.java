package cinemaclub.cinema;

import exceptions.SeatNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;

public class Showing implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    Screen screen;
    String date;
    String time;
    Film film;
    ArrayList<String> takenSeats;

    Showing(Screen screen, String date, String time, Film film, ArrayList<String> takenSeats) {
        this.screen = screen;
        this.date = date;
        this.time = time;
        this.film = film;
        this.takenSeats = takenSeats;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
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

    public Boolean isSeatTaken(String row, int number) throws SeatNotFoundException {
        validateSeat(row, number);
        return takenSeats.contains(row + number);
    }

    void bookSeat(String row, int number) throws SeatNotFoundException {
        validateSeat(row, number);
        takenSeats.add(row + number);
    }

    public void unbookSeat(String row, int number) throws SeatNotFoundException {
        validateSeat(row, number);
        takenSeats.remove(row + number);
    }

    private void validateSeat(String row, int number) throws SeatNotFoundException {
        if (!screen.getSeats().contains(row + number)) {
            throw new SeatNotFoundException();
        }
    }

    @Override
    public String toString() {
        return "Showing{" +
            "screen=" + screen +
            ", date='" + date + '\'' +
            ", time='" + time + '\'' +
            ", film=" + film +
            ", takenSeats=" + takenSeats +
            '}';
    }
}
