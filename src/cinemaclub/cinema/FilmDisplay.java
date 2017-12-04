package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.ScreenRepository;
import exceptions.PastDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

class FilmDisplay {
    //TODO add displaying all showings as well as all films.
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

    private void validateDate(String date) throws PastDateException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String timeNow = LocalDateTime.now().format(formatter);

        if (date.compareTo(timeNow) < 0) {
            throw new PastDateException();
        }
    }

}