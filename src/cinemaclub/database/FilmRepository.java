package cinemaclub.database;

import cinemaclub.cinema.Film;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class containing all database methods related to films.
 * Whenever data is changed in this class, the external txt file database is updated.
 * Serializable allows it to be saved as bytes to an external database file.
 */
public class FilmRepository implements Serializable {

    private DataBase dataBase;
    private Map<String, Film> films = new HashMap<>();

    /**
     * Instantiates the screenRepository with the one database instance to guarantee data safety.
     *
     * @param dataBase dataBase object
     */
    FilmRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    /**
     * Adds a film to the database.
     *
     * @param title film title key
     * @param film film value
     */
    public void addFilm(String title, Film film) {
        films.put(title, film);

        dataBase.updateExternalDB();
    }

    /**
     * Gets all films in the database.
     *
     * @return all films
     */
    public ArrayList<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }

    /**
     * Checks if a film exists in the database.
     *
     * @param title file title key to check for
     * @return true if the file title exists and false otherwise
     */
    public Boolean checkForFilm(String title) {
        return films.containsKey(title);
    }

    /**
     * Gets a film by its title.
     *
     * @param title film title key
     * @return film value
     */
    public Film getFilmByTitle(String title) {
        return films.get(title);
    }

    /**
     * Updates a film title for a film.
     * As the film title is a key, it first removes the old pair and then adds the new one.
     *
     * @param oldTitle old film title to be removed
     * @param newTitle new film title to be added
     * @param film same film
     */
    public void setFilmTitle(String oldTitle, String newTitle, Film film) {
        films.remove(oldTitle);
        films.put(newTitle, film);

        dataBase.updateExternalDB();
    }

    /**
     * Removes a film from the database.
     *
     * @param title film title key to be removed
     */
    public void deleteFilm(String title) {
        films.remove(title);

        dataBase.updateExternalDB();
    }

    /**
     * Updates the external txt file to save any simple changes to the offline database.
     */
    public void updateDB() {
        dataBase.updateExternalDB();
    }
}
