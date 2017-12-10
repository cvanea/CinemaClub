package cinemaclub.database;

import cinemaclub.cinema.Film;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FilmRepository implements Serializable {

    private DataBase dataBase;
    private Map<String, Film> films = new HashMap<>();

    FilmRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void addFilm(String title, Film film) {
        films.put(title, film);

        dataBase.updateExternalDB();
    }

    public ArrayList<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }

    public Boolean checkForFilm(String title) {
        return films.containsKey(title);
    }

    public Film getFilmByTitle(String title) {
        return films.get(title);
    }

    public void setFilmTitle(String oldTitle, String newTitle, Film film) {
        films.remove(oldTitle);
        films.put(newTitle, film);

        dataBase.updateExternalDB();
    }

    public void updateDB() {
        dataBase.updateExternalDB();
    }

    public void deleteFilm(String title) {
        films.remove(title);

        dataBase.updateExternalDB();
    }

}
