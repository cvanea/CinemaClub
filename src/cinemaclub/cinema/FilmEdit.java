package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.FilmRepository;
import exceptions.FilmExistsException;

class FilmEdit {

    private FilmRepository filmRepository;

    FilmEdit() {
        this.filmRepository = DataBase.getFilmRepository();
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
        filmRepository.setFilmImagePath(film);
    }

    void setFilmDescription(Film film, String newDescription) {
        film.setDescription(newDescription);
        filmRepository.setFilmDescription(film);
    }

    void setFilmRunTime(Film film, String newRunTime) {
        film.setRunTime(newRunTime);
        filmRepository.setFilmRunTime(film);
    }

    void deleteFilm(String title) {
        filmRepository.deleteFilm(title);
    }

    private void validateNewFilm(String title) throws FilmExistsException {

        if (filmRepository.checkForFilm(title)) {
            throw new FilmExistsException();
        }
    }
}

