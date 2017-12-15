package exceptions;

public class EmptyDateTimeException extends Exception {
    public EmptyDateTimeException() {
        super("Please select both a date and time");
    }
}
