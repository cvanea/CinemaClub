package exceptions;

public class UserDetailsIncorrectException extends Exception {

    public UserDetailsIncorrectException() {
        super("Wrong details for that username. Please try again.");
    }
}
