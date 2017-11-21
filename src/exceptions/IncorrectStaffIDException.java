package exceptions;

public class IncorrectStaffIDException extends Exception {

    public IncorrectStaffIDException() {
        super("That is an incorrect ID. Please try again.");
    }

}
