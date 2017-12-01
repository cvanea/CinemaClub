package exceptions;

public class NoBookingsException extends Exception {
    public NoBookingsException() {
        super("No booking history! Book a film!");
    }
}
