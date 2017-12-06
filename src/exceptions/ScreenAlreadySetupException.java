package exceptions;

public class ScreenAlreadySetupException extends Exception {
    public ScreenAlreadySetupException() {
        super("That screen is already set up!");
    }
}
