package cinemaclub.cinema;

import exceptions.SeatNotFoundException;

import java.io.Serializable;
import java.util.Map;

/**
 * Object that holds all information for a particular showing.
 * Serializable allows it to be saved as bytes to an external database file.
 */
public class Showing implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private Screen screen;
    private String date;
    private String time;
    private Film film;
    private Map<String, String> takenSeats;

    /**
     * Instantiating a showing requires all showings properties to be passed.
     *
     * @param screen screen of showing
     * @param date date of showing
     * @param time time of showing
     * @param film film showing
     * @param takenSeats map of seats to username
     */
    public Showing(Screen screen, String date, String time, Film film, Map<String, String> takenSeats) {
        this.screen = screen;
        this.date = date;
        this.time = time;
        this.film = film;
        this.takenSeats = takenSeats;
    }

    /**
     * Gets the screen.
     *
     * @return screen
     */
    public Screen getScreen() {
        return screen;
    }

    /**
     * Sets the screen.
     *
     * @param screen screen
     */
    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    /**
     * Gets the date.
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date.
     *
     * @param date date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the time.
     *
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time.
     *
     * @param time time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets the film.
     *
     * @return film
     */
    public Film getFilm() {
        return film;
    }

    /**
     * Sets the film.
     *
     * @param film film
     */
    public void setFilm(Film film) {
        this.film = film;
    }

    /**
     * Gets the map of seats to username
     *
     * @return map of seats to username
     */
    public Map<String, String> getTakenSeats() {
        return takenSeats;
    }

    /**
     * Gets the screen number of the screen.
     *
     * @return screen number
     */
    public Integer getScreenNumber() {
        return screen.getScreenNumber();
    }

    /**
     * Gets the number of available seats for this showing.
     *
     * @return number of available seats
     */
    public int getNumberOfAvailableSeats() {
        return getScreen().getSeats().size() - takenSeats.size();
    }

    /**
     * Gets the film title.
     *
     * @return film title
     */
    public String getFilmTitle() {
        return film.getTitle();
    }

    /**
     * Overrides toString method to clearly show all showings properties.
     *
     * @return string of all showings properties
     */
    @Override
    public String toString() {
        return "Showing{" + "screen=" + screen + ", date='" + date + '\'' + ", time='" + time + '\'' +
            ", film=" + film + ", takenSeats=" + takenSeats + '}';
    }

    /**
     * Converts a showing to the correct csv format.
     *
     * @return converted showing ready to be written to a csv
     */
    String toCsv() {
        return film.getTitle() + ", " + date + ", " + time + ", Screen " + screen.getScreenNumber() +
            ", Taken seats: " + takenSeats.size() + ", Free seats: " + (screen.getSeats().size() -
            takenSeats.size()) + ", " + takenSeats + "\n";
    }

    /**
     * Attempts to book a seat on this particular showing.
     * First it checks that the seat exists.
     *
     * @param username username of user making the booking.
     * @param row seat row
     * @param number seat number
     * @throws SeatNotFoundException checks whether the seat exists
     */
    void bookSeat(String username, String row, int number) throws SeatNotFoundException {
        validateSeat(row, number);
        takenSeats.put(row + number, username);
    }

    /**
     * Attempts to remove a booked seat on this particular showing.
     * First it checks that the seat exists.
     *
     * @param row seat row
     * @param number seat number
     * @throws SeatNotFoundException checks whether the seat exists
     */
    void unbookSeat(String row, int number) throws SeatNotFoundException {
        validateSeat(row, number);
        takenSeats.remove(row + number);
    }

    /**
     * Checks whether a seat is taken for a particular showing.
     * First it checks that the seat exists.
     *
     * @param row seat row
     * @param number seat number
     * @return true is seat is taken and false otherwise
     * @throws SeatNotFoundException checks whether the seat exists
     */
    public Boolean isSeatTaken(String row, int number) throws SeatNotFoundException {
        validateSeat(row, number);
        return takenSeats.containsKey(row + number);
    }

    /**
     * Validates whether a seat exists on the screen for this showing.
     *
     * @param row seat row
     * @param number seat number
     * @throws SeatNotFoundException thrown exception
     */
    private void validateSeat(String row, int number) throws SeatNotFoundException {
        if (!screen.getSeats().contains(row + number)) {
            throw new SeatNotFoundException();
        }
    }
}
