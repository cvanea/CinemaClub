package exceptions;

public class NoPastBookingsException extends Exception {
    public NoPastBookingsException() {
        super("You no past bookings. Go and watch a film!");
    }
}
