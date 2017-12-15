package cinemaclub.user;

import cinemaclub.cinema.Showing;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class for customer users of the cinema system.
 * Inherits most getters and setters from User.
 */
public class Customer extends User {

    private ArrayList<Booking> bookings;

    /**
     * Inherits UserCredentials from User for instantiation but adds bookings.
     *
     * @param userCredentials profile details such as username, first name, email, etc.
     * @param bookings all the customer bookings
     */
    public Customer(UserCredentials userCredentials, ArrayList<Booking> bookings) {
        super(userCredentials);
        this.bookings = bookings;
    }

    /**
     * Gets the seat booked for a particular booking.
     *
     * @param booking booking of which the seat is booked
     * @return booked seat
     */
    public String getSeat(Booking booking) {
        return booking.getSeat();
    }

    /**
     * Gets all bookings for this customer.
     *
     * @return all bookings
     */
    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    /**
     * Sets all bookings for this customer.
     *
     * @param bookings all bookings
     */
    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * Adds a booking to all bookings.
     *
     * @param booking booking to add
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    /**
     * Gets all the bookings that match a certain film title.
     *
     * @param title
     * @return
     */
    public ArrayList<Booking> getBookingsByTitle(String title) {
        ArrayList<Booking> titleBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getFilm().getTitle().equals(title)) {
                titleBookings.add(booking);
            }
        }
        return titleBookings;
    }

    /**
     * Gets the booking which matches the title, date, and time.
     *
     * @param title film title
     * @param date showing date
     * @param time showing time
     * @return matched booking
     */
    public Booking getBookingsByTitleDateTime(String title, String date, String time) {
        for (Booking booking : bookings) {
            if (booking.getFilm().getTitle().equals(title) && booking.getDate().equals(date) &&
                booking.getTime().equals(time)) {
                return booking;
            }
        }
        return null;
    }

    /**
     * Gets a booking by the showing and seat booked.
     *
     * @param showing showing booked
     * @param seat seat booked
     * @return matched booking
     */
    public Booking getBooking(Showing showing, String seat) {
        for (Booking booking : bookings) {
            if (booking.getShowing() == showing && booking.getSeat().equals(seat)) {
                return booking;
            }
        }
        return null;
    }

    /**
     * Checks if the customer has made a certain booking.
     *
     * @param booking booking to check
     * @return true if the customer made the booking and false otherwise
     */
    public Boolean containsBooking(Booking booking) {
        return bookings.contains(booking);
    }

    /**
     * Removes a booking from the customer's total bookings.
     *
     * @param booking booking to be removed
     */
    public void deleteBooking(Booking booking) {
        bookings.remove(booking);
    }

    /**
     * Checks if the customer has any bookings.
     *
     * @return true if the customer has no bookings and false otherwise.
     */
    public Boolean noExistingBookings() {
        return this.getBookings().isEmpty();
    }

    /**
     * Overrides toString method to clearly show all user properties.
     * This takes the toString method from the userCredentials on the customer
     * and adds the bookings.
     *
     * @return string of all user properties
     */
    @Override
    public String toString() {
        return super.toString() + ", " + Arrays.toString(bookings.toArray());
    }
}
