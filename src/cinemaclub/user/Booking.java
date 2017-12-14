package cinemaclub.user;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Screen;
import cinemaclub.cinema.Showing;

import java.io.Serializable;

/**
 * Object that holds all information for a particular booking.
 * Bookings are held in the user package as they are only ever instantiated as a property of a customer.
 * Serializable allows it to be saved as bytes to an external database file.
 */
public class Booking implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private Showing showing;
    private String seat;

    /**
     * Instantiating a booking requires all booking properties to be passed.
     *
     * @param showing booked showing
     * @param seat booked seat
     */
    public Booking(Showing showing, String seat) {
        this.showing = showing;
        this.seat = seat;
    }

    /**
     * Gets the showing.
     *
     * @return showing
     */
    public Showing getShowing() {
        return showing;
    }

    /**
     * Sets the showing.
     *
     * @param showing showing
     */
    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    /**
     * Gets the seat.
     *
     * @return seat
     */
    public String getSeat() {
        return seat;
    }

    /**
     * Sets the seat.
     *
     * @param seat seat
     */
    public void setSeat(String seat) {
        this.seat = seat;
    }

    /**
     * Gets the screen from the showing.
     *
     * @return screen
     */
    public Screen getScreen() {
        return showing.getScreen();
    }

    /**
     * Gets the date from the showing.
     *
     * @return date
     */
    public String getDate() {
        return showing.getDate();
    }

    /**
     * Gets the time from the showing.
     *
     * @return time
     */
    public String getTime() {
        return showing.getTime();
    }

    /**
     * Gets the film from the showing.
     *
     * @return film
     */
    public Film getFilm() {
        return showing.getFilm();
    }

    /**
     * Gets the film title from the showing.
     *
     * @return film title
     */
    public String getTitle() { return  showing.getFilm().getTitle();}

    /**
     * Overrides toString method to clearly show all booking properties.
     *
     * @return string of all booking properties
     */
    @Override
    public String toString() {
        return "Booking{" + "showing=" + showing + ", seat='" + seat + '\'' + '}';
    }
}
