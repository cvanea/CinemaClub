package exceptions;

public class UsernameTakenException extends Exception {

    public UsernameTakenException() {
        super("That username already exists. Please try again");
    }
}
