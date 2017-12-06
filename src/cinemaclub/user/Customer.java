package cinemaclub.user;

import java.util.ArrayList;
import java.util.Arrays;

public class Customer extends User {

    private ArrayList<Booking> bookings;

    public Customer(UserCredentials userCredentials, ArrayList<Booking> bookings) {
        super(userCredentials);
        this.bookings = bookings;
    }

    public String IExist() {
        return "I exist as a customer!";
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public Booking getBookingByTitle(String title) {
        //TODO WONT WORK FOR MULTIPLE BOOKINGS OF SAME TITLE.
        for (Booking booking : bookings) {
            if (booking.getFilm().getTitle().equals(title)) {
                return booking;
            }
        }
        return null;
    }

    public Boolean containsBooking(Booking booking) {
        return bookings.contains(booking);
    }

    public void deleteBooking(Booking booking) {
        bookings.remove(booking);
    }

    public Boolean noExistingBookings() {
        return this.getBookings().isEmpty();
    }

    @Override
    public String toString() {
        return super.toString() + ", " + Arrays.toString(bookings.toArray());
    }
}
