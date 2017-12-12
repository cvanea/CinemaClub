package exceptions;

public class MissingRowColException extends Exception {
    public MissingRowColException() {
        super("Please select both the number of rows and seats per row");
    }
}
