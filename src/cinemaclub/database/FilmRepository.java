package cinemaclub.database;

import cinemaclub.cinema.Film;

import java.io.Serializable;
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

    public Boolean checkForFilm(String title) {
        return films.containsKey(title);
    }

    public Film getFilm(String title) {
        return films.get(title);
    }

    public void setFilmTitle(String oldTitle, String newTitle, Film film) {
        films.remove(oldTitle);
        films.put(newTitle, film);

        dataBase.updateExternalDB();
    }

    public void setFilmImagePath(Film film) {
        films.put(film.getTitle(), film);

        dataBase.updateExternalDB();
    }

    public void setFilmDescription(Film film) {
        films.put(film.getTitle(), film);

        dataBase.updateExternalDB();
    }

    public void setFilmRunTime(Film film) {
        films.put(film.getTitle(), film);

        dataBase.updateExternalDB();
    }

    public void deleteFilm(String title) {
        films.remove(title);

        dataBase.updateExternalDB();
    }

}
