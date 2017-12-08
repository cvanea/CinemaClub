package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.FilmRepository;
import cinemaclub.database.ScreenRepository;
import exceptions.FilmExistsException;
import exceptions.ShowingAlreadyExistsException;

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

    Film getFilmDetailsByTitle(String title) {
        return filmRepository.getFilmByTitle(title);
    }

    void addShowing(Screen screen, String date, String time, Film film) throws ShowingAlreadyExistsException {
        validateShowing(screen, date, time);
        screenRepository.addShowing(screen, new Showing(screen, date, time, film, new ArrayList<>()));
    }

    void deleteShowing(Screen screen, String date, String time) {
        screenRepository.deleteShowing(screen, date, time);
    }

    void addScreen(Integer screenNumber, int numberRow, int seatsPerRow) {
        screenRepository.addScreen(new Screen(screenNumber, numberRow, seatsPerRow));
    }

    private void validateNewFilm(String title) throws FilmExistsException {
        if (filmRepository.checkForFilm(title)) {
            throw new FilmExistsException();
        }
    }

    private void validateShowing(Screen screen, String date, String time) throws ShowingAlreadyExistsException {
        if (screenRepository.getShowingByDateTime(screen, date, time) != null) {
            throw new ShowingAlreadyExistsException();
        }
    }
}