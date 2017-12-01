package cinemaclub.user;

public class Customer extends User {

    Bookings bookings;

    public Customer(UserCredentials userCredentials) {
        super(userCredentials);
//        this.bookings = bookings;
    }

    public String IExist() {
        return "I exist as a customer!";
    }

    public Bookings getBookings() {
        return bookings;
    }

    public void setBookings(Bookings bookings) {
        this.bookings = bookings;
    }
}
