package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.ScreenRepository;
import exceptions.PastDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Cinema system which queries the screens database for showings information.
 */
class FilmDisplay {
    private ScreenRepository screenRepository;

    /**
     * Gets the singleton database instance relevant to the film display system.
     */
    FilmDisplay() {
        this.screenRepository = DataBase.getScreenRepository();
    }

    /**
     * Gets all films that are showing on a particular date.
     * First it validates that the date is in the future.
     *
     * @param date date of film showings
     * @return all films showing on the date
     * @throws PastDateException only allows future showings to get returned
     */
    ArrayList<Film> getFilmsByDate(String date) throws PastDateException {
        validateDate(date);
        return screenRepository.getAllFilmsByDate(date);
    }

    /**
     * Get showings that show on a certain date and screen.
     * * First it validates that the date is in the future.
     *
     * @param date date of showings
     * @param screen screen of showings
     * @return showings matching the date and screen
     * @throws PastDateException prevents past showings from being matched
     */
    ArrayList<Showing> getShowingsByDate(String date, Screen screen) throws PastDateException {
        validateDate(date);
        return screenRepository.getShowingsByDateScreen(screen, date);
    }

    /**
     * Gets all showings in the screens database.
     *
     * @return all showings
     */
    ArrayList<Showing> getAllShowings() {
        return screenRepository.getAllShowings();
    }

    /**
     * Get all film times for a particular film.
     *
     * @param film film searching by
     * @return all times for that string
     */
    ArrayList<String> getAllTimesByFilm(Film film) {
        return screenRepository.getAllTimesByFilm(film);
    }

    /**
     * Get film dates times for a particular film on a screen.
     *
     * @param screen screen of showing
     * @param film film showing
     * @return dates matching the screen and film
     */
    ArrayList<String> getDatesByFilm(Screen screen, Film film) {
        return screenRepository.getDatesByFilm(screen, film);
    }

    /**
     * Gets all showings of a particular film.
     *
     * @param film film of the showings
     * @return all showings of the film
     */
    ArrayList<Showing> getAllShowingsByFilm(Film film) {
        return screenRepository.getAllShowingsByFilm(film);
    }

    /**
     * Get showing by the date, time, and film of the showing.
     * Will always be unique since, by design, showings cannot be the same across screens.
     *
     * @param date date of showing
     * @param time time of showing
     * @param film film of showing
     * @return the showing matching the date, time, and film
     */
    Showing getShowingByDateTimeFilm(String date, String time, Film film) {
        return screenRepository.getShowingByDateTimeFilm(date, time, film);
    }

    /**
     * Get showing by the date, time, and screen of the showing.
     *
     * @param screen screen of showing
     * @param date date of showing
     * @param time time of showing
     * @return showing matching the screen, date, and time
     */
    Showing getShowingByDateTimeScreen(Screen screen, String date, String time) {
        return screenRepository.getShowingByDateTimeScreen(screen, date, time);
    }

    /**
     * Validates that a queried date is in the future by checking against the current time.
     * All dates and times are set to the "yyyy-MM-dd HH:mm" format.
     *
     * @param date date to be validated
     * @throws PastDateException exception thrown
     */
    private void validateDate(String date) throws PastDateException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String timeNow = LocalDateTime.now().format(formatter);

        if (date.compareTo(timeNow) < 0) {
            throw new PastDateException();
        }
    }

}