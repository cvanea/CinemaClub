package exceptions;

public class NoFutureBookingsException extends Exception {
    public NoFutureBookingsException() {
        super("There are no future bookings! Make a booking");
    }
}
