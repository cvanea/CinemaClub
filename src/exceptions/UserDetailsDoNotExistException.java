package exceptions;

public class UserDetailsDoNotExistException extends Exception {

    public UserDetailsDoNotExistException() {
        super("There is no account with those details. Please try again");
    }

}
