package exceptions;

public class IncorrectStaffIDException extends Exception {

    public IncorrectStaffIDException() {
        super("That ID does not exist. Please try again.");
    }

}
