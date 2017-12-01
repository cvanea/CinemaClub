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

    @Override
    public String toString() {
        return super.toString() + ", " + Arrays.toString(bookings.toArray());
    }
}
