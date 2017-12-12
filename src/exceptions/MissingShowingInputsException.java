package exceptions;

public class MissingShowingInputsException extends Exception {
    public MissingShowingInputsException() {
        super("Please fill in all showing fields.");
    }
}
