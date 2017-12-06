package exceptions;

public class PastDateException extends Exception {
    public PastDateException() {
        super("That date has past. Please choose a future date.");
    }
}
