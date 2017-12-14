package cinemaclub.user;

import cinemaclub.cinema.Showing;

import java.util.ArrayList;
import java.util.Arrays;

public class Customer extends User {

    private ArrayList<Booking> bookings;

    public Customer(UserCredentials userCredentials, ArrayList<Booking> bookings) {
        super(userCredentials);
        this.bookings = bookings;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public String getSeat(Booking booking) {
        return booking.getSeat();
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public ArrayList<Booking> getBookingsByTitle(String title) {
        ArrayList<Booking> titleBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getFilm().getTitle().equals(title)) {
                titleBookings.add(booking);
            }
        }
        return titleBookings;
    }

    public Booking getBookingsByTitleDateTime(String title, String date, String time) {
        for (Booking booking : bookings) {
            if (booking.getFilm().getTitle().equals(title) && booking.getDate().equals(date) &&
                booking.getTime().equals(time)) {
                return booking;
            }
        }
        return null;
    }

    public Booking getBookingByShowing(Showing showing) {
        for (Booking booking : bookings) {
            if (booking.getShowing() == showing) {
                return booking;
            }
        }
        return null;
    }

    public Booking getBooking(Showing showing, String seat) {
        for (Booking booking : bookings) {
            if (booking.getShowing() == showing && booking.getSeat().equals(seat)) {
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
