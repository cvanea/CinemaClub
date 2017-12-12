package exceptions;

public class NoFilmsToDisplayException extends Exception{
    public NoFilmsToDisplayException() { super("There are no films to display on this date. Please select another date."); }
}
