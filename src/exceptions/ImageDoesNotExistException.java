package exceptions;

public class ImageDoesNotExistException extends Exception {
        public ImageDoesNotExistException() {
            super("Image file does not exist!");
        }
}
