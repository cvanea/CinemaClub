package exceptions;

public class ShowingAlreadyExistsException extends Exception {
    public ShowingAlreadyExistsException() {
        super("There is already a showing at that time! Please choose a different time or date");
    }
}
