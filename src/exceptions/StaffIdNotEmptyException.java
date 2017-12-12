package exceptions;

public class StaffIdNotEmptyException extends Exception {
    public StaffIdNotEmptyException() {
        super("That staff ID is already assigned!");
    }
}
