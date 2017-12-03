package exceptions;

public class SeatNotFoundException extends Exception {
    public SeatNotFoundException() {
        super("That seat does not exists on this screen! Choose a different seat.");
    }
}
