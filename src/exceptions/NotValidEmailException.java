package exceptions;

public class NotValidEmailException extends Exception {
    public NotValidEmailException() {
        super("That isn't a valid email format.");
    }
}
