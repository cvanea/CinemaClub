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
     * @param screen
     */
    public void addScreen(Screen screen) {
        showings.put(screen, new ArrayList<>());

        dataBase.updateExternalDB();
    }

    /**
     * @param screen
     */
    public void deleteScreen(Screen screen) {
        showings.remove(screen);

        dataBase.updateExternalDB();
    }

    /**
     * @return
     */
    public ArrayList<Screen> getAllScreens() {
        ArrayList<Screen> screens = new ArrayList<>();
        screens.addAll(showings.keySet());
        return screens;
    }

    /**
     * @param screen
     * @return
     */
    public ArrayList<Showing> getScreenShowings(Screen screen) {
        return showings.get(screen);
    }

    /**
     * @param date
     * @return
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
     * @param screen
     * @param date
     * @return
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
     * @param screen
     * @param date
     * @param time
     * @return
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
     * @param date
     * @param time
     * @param film
     * @return
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
     * @param film
     * @return
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
     * @param screen
     * @param film
     * @return
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
     * @param film
     * @return
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
     * @return
     */
    public ArrayList<Showing> getAllShowings() {
        ArrayList<Showing> allShowings = new ArrayList<>();

        for (Screen screen : showings.keySet()) {
            allShowings.addAll(showings.get(screen));
        }

        return allShowings;
    }

    /**
     * @param screen
     * @param showing
     */
    public void addShowing(Screen screen, Showing showing) {
        ArrayList<Showing> showingsPerScreen = showings.get(screen);
        showingsPerScreen.add(showing);
        showings.put(screen, showingsPerScreen);

        dataBase.updateExternalDB();
    }

    /**
     * @param screen
     * @param date
     * @param time
     */
    public void deleteShowing(Screen screen, String date, String time) {
        ArrayList<Showing> showingsByScreen = getScreenShowings(screen);
        Showing showing = getShowingByDateTimeScreen(screen, date, time);
        showingsByScreen.remove(showing);
        showings.put(screen, showingsByScreen);

        dataBase.updateExternalDB();
    }

    /**
     * @return
     */
    public Map<Screen, ArrayList<Showing>> getShowings() {
        return showings;
    }

    /**
     *
     */
    public void updateDB() {
        dataBase.updateExternalDB();
    }

}
