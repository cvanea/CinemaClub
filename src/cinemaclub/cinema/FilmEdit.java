package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import exceptions.FilmExistsException;

class FilmEdit {

    private DataBase dataBase;

    FilmEdit() {
        this.dataBase = DataBase.getInstance();
    }

    void addFilm(String title, String imagePath, String description, int runTime) throws FilmExistsException {
        validateNewFilm(title);
        Film film = new Film(title, imagePath, description, runTime);
        dataBase.addFilm(title, film);
    }

    void setFilmTitle(Film film, String newTitle) throws FilmExistsException {
        validateNewFilm(newTitle);
        String oldTitle = film.getTitle();
        film.setTitle(newTitle);
        dataBase.setFilmTitle(oldTitle, newTitle, film);
    }

    void setFilmImagePath(Film film, String newImagePath) {
        film.setImagePath(newImagePath);
        dataBase.setFilmImagePath(film);
    }

    void setFilmDecription(Film film, String newDescription) {
        film.setDescription(newDescription);
        dataBase.setFilmDescription(film);
    }

    void setFilmRunTime(Film film, int newRunTime) {
        film.setRunTime(newRunTime);
        dataBase.setFilmRunTime(film);
    }

    void deleteFilm(String title) {
        dataBase.deleteUser(title);
    }

    private void validateNewFilm(String title) throws FilmExistsException {

        if (dataBase.checkForFilm(title)) {
            throw new FilmExistsException();
        }
    }
}

