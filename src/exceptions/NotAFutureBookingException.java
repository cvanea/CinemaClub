package exceptions;

public class NotAFutureBookingException extends Exception {
    public NotAFutureBookingException() {
        super("There is no booking with that name!");
    }
}
