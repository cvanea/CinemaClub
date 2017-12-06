package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.ScreenRepository;
import exceptions.PastDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

class FilmDisplay {
    //TODO add method that checks across all screens for a given date
    private ScreenRepository screenRepository;

    FilmDisplay() {
        this.screenRepository = DataBase.getScreenRepository();
    }

    ArrayList<Film> getFilmsByDateScreen(String date, Screen screen) throws PastDateException {
        validateDate(date);
        return screenRepository.getFilmsByDate(screen, date);
    }

    Map<String, Film> getShowingsByDate(String date, Screen screen) throws PastDateException {
        validateDate(date);
        return screenRepository.getShowingsByDate(screen, date);
    }

    ArrayList<String> getTimesByFilm(Film film) {
        return screenRepository.getTimesByFilm(film);
    }

    private void validateDate(String date) throws PastDateException {
        //TODO MAKE SURE DATE WITHOUT TIME WORKS HERE
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String timeNow = LocalDateTime.now().format(formatter);

        if (date.compareTo(timeNow) < 0) {
            throw new PastDateException();
        }
    }

}