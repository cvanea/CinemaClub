package exceptions;

public class ScreenNumberAlreadyExistsException extends Exception {
    public ScreenNumberAlreadyExistsException() {
        super("There is already a screen with that number. Please choose another.");
    }
}
