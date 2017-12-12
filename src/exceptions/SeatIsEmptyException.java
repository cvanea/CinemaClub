package exceptions;

public class SeatIsEmptyException extends Exception {
    public SeatIsEmptyException() {
        super("No seat selected.");
    }

}
