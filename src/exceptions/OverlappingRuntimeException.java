package exceptions;

public class OverlappingRuntimeException extends Exception {
    public OverlappingRuntimeException() {
        super("This showing overlaps with an existing showing! Please choose another time or date.");
    }
}
