package exceptions;

public class DateTimeNullException extends Exception {
    public DateTimeNullException() {
        super("The Date, Time field or both are not selected.");
    }
}
