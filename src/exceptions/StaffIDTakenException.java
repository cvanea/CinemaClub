package exceptions;

public class StaffIDTakenException extends Exception {

    public StaffIDTakenException() {
        super("That ID is already assigned. Please try again.");
    }

}
