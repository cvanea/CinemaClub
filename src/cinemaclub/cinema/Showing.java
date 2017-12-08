package cinemaclub.cinema;

import exceptions.SeatNotFoundException;

import java.io.Serializable;
import java.util.Map;

public class Showing implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private Screen screen;
    private String date;
    private String time;
    private Film film;
//    private ArrayList<String> takenSeats;
    private Map<String, String> takenSeats;

    Showing(Screen screen, String date, String time, Film film, Map<String, String> takenSeats) {
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

    public Map<String, String> getTakenSeats() {
        return takenSeats;
    }

    public void setTakenSeats(Map<String, String> takenSeats) {
        this.takenSeats = takenSeats;
    }

    public Boolean isSeatTaken(String row, int number) throws SeatNotFoundException {
        validateSeat(row, number);

        return takenSeats.containsKey(row + number);
    }

    void bookSeat(String username, String row, int number) throws SeatNotFoundException {
        validateSeat(row, number);
        takenSeats.put(row + number, username);
    }

    void unbookSeat(String row, int number) throws SeatNotFoundException {
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
