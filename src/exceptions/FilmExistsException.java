package exceptions;

public class FilmExistsException extends Exception {
    public FilmExistsException() {
        super("That film already exists at this cinema!");
    }
}
