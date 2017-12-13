package exceptions;

public class MissingFilmInputsException extends Exception {
    public MissingFilmInputsException() {
        super("Please fill in all the fields.");
    }
}
