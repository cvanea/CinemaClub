package exceptions;

public class EmptyUserInputException extends Exception {
    public EmptyUserInputException() {
        super("You haven't filled in all the fields. Please fill in the missing fields.");
    }
}
