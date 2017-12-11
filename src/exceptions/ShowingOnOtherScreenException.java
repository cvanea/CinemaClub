package exceptions;

public class ShowingOnOtherScreenException extends Exception {
    public ShowingOnOtherScreenException() {
        super("This is already showing at this time and date on another screen!");
    }
}
