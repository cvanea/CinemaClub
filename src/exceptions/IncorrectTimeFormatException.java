package exceptions;

public class IncorrectTimeFormatException extends Exception {
    public IncorrectTimeFormatException() {
        super("Please enter the time in the format: HH:mm");
    }
}
