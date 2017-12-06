package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.FilmRepository;
import cinemaclub.database.ScreenRepository;
import exceptions.FilmExistsException;

import java.util.ArrayList;

class FilmEdit {

    private FilmRepository filmRepository;
    private ScreenRepository screenRepository;

    FilmEdit() {
        this.filmRepository = DataBase.getFilmRepository();
        this.screenRepository = DataBase.getScreenRepository();
    }

    void addFilm(String title, String imagePath, String description, String runTime) throws FilmExistsException {
        validateNewFilm(title);
        Film film = new Film(title, imagePath, description, runTime);
        filmRepository.addFilm(title, film);
    }

    void setFilmTitle(Film film, String newTitle) throws FilmExistsException {
        validateNewFilm(newTitle);
        String oldTitle = film.getTitle();
        film.setTitle(newTitle);
        filmRepository.setFilmTitle(oldTitle, newTitle, film);
    }

    void setFilmImagePath(Film film, String newImagePath) {
        film.setImagePath(newImagePath);
        filmRepository.updateDB();
    }

    void setFilmDescription(Film film, String newDescription) {
        film.setDescription(newDescription);
        filmRepository.updateDB();
    }

    void setFilmRunTime(Film film, String newRunTime) {
        film.setRunTime(newRunTime);
        filmRepository.updateDB();
    }

    void deleteFilm(String title) {
        filmRepository.deleteFilm(title);
    }

    ArrayList<Film> displayAllFilms() {
        return filmRepository.getAllFilms();
    }

    Film getFilmDetails(String title) {
        return filmRepository.getFilm(title);
    }

    void addFilmToShowings(Screen screen, String date, String time, Film film) {
        screenRepository.addShowing(screen, date, time, film);
    }

    void deleteShowing(Screen screen, String date, String time) {
        screenRepository.deleteShowing(screen, date, time);
    }

    private void validateNewFilm(String title) throws FilmExistsException {

        if (filmRepository.checkForFilm(title)) {
            throw new FilmExistsException();
        }
    }
}