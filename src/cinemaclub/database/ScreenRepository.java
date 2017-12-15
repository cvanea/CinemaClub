package cinemaclub.database;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Screen;
import cinemaclub.cinema.Showing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class containing all database methods related to screens and showings.
 * Whenever data is changed in this class, the external txt file database is updated.
 * Serializable allows it to be saved as bytes to an external database file.
 */
public class ScreenRepository implements Serializable {

    private DataBase dataBase;

    private Map<Screen, ArrayList<Showing>> showings = new HashMap<>();

    /**
     * Instantiates the screenRepository with the one database instance to guarantee data safety.
     *
     * @param dataBase dataBase object
     */
    ScreenRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    /**
     * Adds a new screen to the database with no showings.
     *
     * @param screen new screen to be added
     */
    public void addScreen(Screen screen) {
        showings.put(screen, new ArrayList<>());

        dataBase.updateExternalDB();
    }

    /**
     * Removes a screen from the database.
     *
     * @param screen screen to be removed
     */
    public void deleteScreen(Screen screen) {
        showings.remove(screen);

        dataBase.updateExternalDB();
    }

    /**
     * Gets all screens on the database without showings information.
     *
     * @return all screens
     */
    public ArrayList<Screen> getAllScreens() {
        ArrayList<Screen> screens = new ArrayList<>();
        screens.addAll(showings.keySet());
        return screens;
    }

    /**
     * Gets all showings for a given screen.
     *
     * @param screen screen key
     * @return all showings paired to the key
     */
    private ArrayList<Showing> getScreenShowings(Screen screen) {
        return showings.get(screen);
    }

    /**
     * Gets all films on a given date.
     *
     * @param date date to match for films
     * @return matched films across showings and screens.
     */
    public ArrayList<Film> getAllFilmsByDate(String date) {
        ArrayList<Showing> allShowings = new ArrayList<>();
        ArrayList<Film> films = new ArrayList<>();

        for (Screen screen : showings.keySet()) {
            allShowings.addAll(showings.get(screen));
        }

        for (Showing showing : allShowings) {
            if (showing.getDate().equals(date)) {
                films.add(showing.getFilm());
            }
        }
        return films;
    }

    /**
     * Gets all showings for a given date and screen.
     *
     * @param screen screen to match for showings
     * @param date date to filter showings
     * @return all showings on that date and screen
     */
    public ArrayList<Showing> getShowingsByDateScreen(Screen screen, String date) {
        ArrayList<Showing> showingsByScreen = getScreenShowings(screen);
        ArrayList<Showing> showingsByDate = new ArrayList<>();

        for (Showing showing : showingsByScreen) {
            if (showing.getDate().equals(date)) {
                showingsByDate.add(showing);
            }
        }
        return showingsByDate;
    }

    /**
     * Gets a unique showing for a given screen, date, and time.
     *
     * @param screen screen to match for showings
     * @param date date to filter showings
     * @param time time to filter showings
     * @return unique matched showing
     */
    public Showing getShowingByDateTimeScreen(Screen screen, String date, String time) {
        ArrayList<Showing> showingsByScreen = new ArrayList<>();

        showingsByScreen.addAll(showings.get(screen));

        for (Showing showing : showingsByScreen) {
            if (showing.getDate().equals(date) && showing.getTime().equals(time)) {
                return showing;
            }
        }
        return null;
    }

    /**
     * Gets a unique showing for a given film, date, and time.
     *
     * @param date date to filter showings
     * @param time time to filter showings
     * @param film film to filter showings
     * @return unique matched showing
     */
    public Showing getShowingByDateTimeFilm(String date, String time, Film film) {
        ArrayList<Showing> allShowings = new ArrayList<>();

        for (Screen screen : showings.keySet()) {
            allShowings.addAll(showings.get(screen));
        }

        for (Showing showing : allShowings) {
            if (showing.getDate().equals(date) && showing.getTime().equals(time) && showing.getFilm() == film) {
                return showing;
            }
        }
        return null;
    }

    /**
     * Gets all times for a given film.
     *
     * @param film film to match against
     * @return all times across screens and dates for film
     */
    public ArrayList<String> getAllTimesByFilm(Film film) {
        ArrayList<Showing> allShowings = new ArrayList<>();
        ArrayList<String> timesByFilm = new ArrayList<>();

        for (Screen screen : showings.keySet()) {
            allShowings.addAll(showings.get(screen));
        }

        for (Showing showing : allShowings) {
            if (showing.getFilm() == film) {
                timesByFilm.add(showing.getTime());
            }
        }
        return timesByFilm;
    }

    /**
     * Gets all dates for a given film and screen.
     *
     * @param screen screen to match against
     * @param film film to filter showings by
     * @return all matched dates
     */
    public ArrayList<String> getDatesByFilm(Screen screen, Film film) {
        ArrayList<Showing> showingsByScreen = getScreenShowings(screen);
        ArrayList<String> dates = new ArrayList<>();

        for (Showing showing : showingsByScreen) {
            if (showing.getFilm() == film) {
                dates.add(showing.getDate());
            }
        }
        return dates;
    }

    /**
     * Gets all showings of a given film.
     *
     * @param film film to match against
     * @return all showings of a film across screens, times, and dates
     */
    public ArrayList<Showing> getAllShowingsByFilm(Film film) {
        ArrayList<Showing> allShowings = new ArrayList<>();
        ArrayList<Showing> showingsByFilm = new ArrayList<>();

        for (Screen screen : showings.keySet()) {
            allShowings.addAll(showings.get(screen));
        }

        for (Showing showing : allShowings) {
            if (showing.getFilm() == film) {
                showingsByFilm.add(showing);
            }
        }
        return showingsByFilm;
    }

    /**
     * Gets all showings regardless of screens, dates, times, or film.
     *
     * @return all showings at the cinema
     */
    public ArrayList<Showing> getAllShowings() {
        ArrayList<Showing> allShowings = new ArrayList<>();

        for (Screen screen : showings.keySet()) {
            allShowings.addAll(showings.get(screen));
        }

        return allShowings;
    }

    /**
     * Adds a showing to a screen.
     *
     * @param screen screen on which to add the new showing
     * @param showing showing to be added
     */
    public void addShowing(Screen screen, Showing showing) {
        ArrayList<Showing> showingsPerScreen = showings.get(screen);
        showingsPerScreen.add(showing);
        showings.put(screen, showingsPerScreen);

        dataBase.updateExternalDB();
    }

    /**
     * Gets the map of all screens and their respective showings.
     *
     * @return map of screens/showings pairs
     */
    public Map<Screen, ArrayList<Showing>> getShowings() {
        return showings;
    }

    /**
     * Removes a showing from a given screen, by its date and time.
     *
     * @param screen screen from which the showing is removed
     * @param date date to find the showing
     * @param time time to find the showing
     */
    public void deleteShowing(Screen screen, String date, String time) {
        ArrayList<Showing> showingsByScreen = getScreenShowings(screen);
        Showing showing = getShowingByDateTimeScreen(screen, date, time);
        showingsByScreen.remove(showing);
        showings.put(screen, showingsByScreen);

        dataBase.updateExternalDB();
    }

    /**
     * Updates the external txt file to save any simple changes to the offline database.
     */
    public void updateDB() {
        dataBase.updateExternalDB();
    }

}
