package exceptions;

public class SeatAlreadyTakenException extends Exception {
    public SeatAlreadyTakenException() {
        super("That seat is already taken! Please choose another.");
    }

}
