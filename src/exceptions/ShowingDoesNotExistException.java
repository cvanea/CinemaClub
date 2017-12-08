package exceptions;

public class ShowingDoesNotExistException extends Exception {
    public ShowingDoesNotExistException() {
        super("That isn't a showing! Please choose something else.");
    }
}
