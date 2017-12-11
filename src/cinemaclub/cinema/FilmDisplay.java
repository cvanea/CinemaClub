package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.ScreenRepository;
import exceptions.PastDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class FilmDisplay {
    private ScreenRepository screenRepository;

    FilmDisplay() {
        this.screenRepository = DataBase.getScreenRepository();
    }

    ArrayList<Film> getFilmsByDate(String date) throws PastDateException {
        validateDate(date);
        return screenRepository.getAllFilmsByDate(date);
    }

    ArrayList<Showing> getShowingsByDate(String date, Screen screen) throws PastDateException {
        validateDate(date);
        return screenRepository.getShowingsByDateScreen(screen, date);
    }

    ArrayList<Showing> getAllShowingsByDate(String date) throws PastDateException {
        validateDate(date);
        return screenRepository.getAllShowingsByDate(date);
    }

    ArrayList<Showing> getAllShowings() {
        return screenRepository.getAllShowings();
    }

    ArrayList<String> getAllTimesByFilm(Film film) {
        return screenRepository.getAllTimesByFilm(film);
    }

    ArrayList<String> getDatesByFilm(Screen screen, Film film) {
        return screenRepository.getDatesByFilm(screen, film);
    }

    ArrayList<Showing> getAllShowingsByFilm(Film film) {
        return screenRepository.getAllShowingsByFilm(film);
    }

    Showing getShowingByDateTime(String date, String time) {
        return screenRepository.getShowingByDateTime(date, time);
    }

    Showing getShowingByDateTimeFilm(String date, String time, Film film) {
        return screenRepository.getShowingByDateTimeFilm(date, time, film);
    }

    Showing getShowingByDateTimeScreen(Screen screen, String date, String time) {
        return screenRepository.getShowingByDateTimeScreen(screen, date, time);
    }

    Screen getScreenByNumber(Integer number) {
        return screenRepository.getScreenByNumber(number);
    }

    private void validateDate(String date) throws PastDateException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String timeNow = LocalDateTime.now().format(formatter);

        if (date.compareTo(timeNow) < 0) {
            throw new PastDateException();
        }
    }

}