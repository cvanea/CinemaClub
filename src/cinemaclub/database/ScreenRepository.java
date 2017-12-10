package cinemaclub.database;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Screen;
import cinemaclub.cinema.Showing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScreenRepository implements Serializable {

    private DataBase dataBase;

    private ArrayList<Showing> showingsWithoutScreen = new ArrayList<>();
    private Map<Screen, ArrayList<Showing>> showings = new HashMap<>();

    ScreenRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void addScreen(Screen screen) {
        showings.put(screen, showingsWithoutScreen);
    }

    public ArrayList<Showing> getScreenShowings(Screen screen) {
        return showings.get(screen);
    }

    public ArrayList<Film> getFilmsByDate(Screen screen, String date) {
        ArrayList<Showing> showingsByScreen = getScreenShowings(screen);
        ArrayList<Film> films = new ArrayList<>();

        for (Showing showing : showingsByScreen) {
            if (showing.getDate().equals(date)) {
                films.add(showing.getFilm());
            }
        }
        return films;
    }

    public ArrayList<Showing> getAllShowingsByDate(String date) {
        ArrayList<Showing> allShowingsByDate = new ArrayList<>();

        for (Screen screen : showings.keySet()) {
            allShowingsByDate.addAll(showings.get(screen));
        }
        return allShowingsByDate;
    }

    public ArrayList<Showing> getShowingsByDate(Screen screen, String date) {
        ArrayList<Showing> showingsByScreen = getScreenShowings(screen);
        ArrayList<Showing> showingsByDate = new ArrayList<>();

        for (Showing showing : showingsByScreen) {
            if (showing.getDate().equals(date)) {
                showingsByDate.add(showing);
            }
        }
        return showingsByDate;
    }

    public Showing getShowingByDateTime(Screen screen, String date, String time) {

        for (Showing showing : showingsWithoutScreen) {
            if (showing.getDate().equals(date) && showing.getTime().equals(time)) {
                return showing;
            }
        }
        return null;
    }

    public ArrayList<String> getTimesByFilm(Screen screen, Film film) {
        ArrayList<Showing> showingsByScreen = getScreenShowings(screen);
        ArrayList<String> times = new ArrayList<>();

        for (Showing showing : showingsByScreen) {
            if (showing.getFilm() == film) {
                times.add(showing.getTime());
            }
        }
        return times;
    }

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

    public ArrayList<Showing> getAllShowingsByFilm(Screen screen, Film film) {
        ArrayList<Showing> showingsByScreen = getScreenShowings(screen);
        ArrayList<Showing> showingsByFilm = new ArrayList<>();

        for (Showing showing : showingsByScreen) {
            if (showing.getFilm() == film) {
                showingsByFilm.add(showing);
            }
        }
        return showingsByFilm;
    }

    public ArrayList<Showing> getAllShowings() {
        return showingsWithoutScreen;
    }

    public void addShowing(Screen screen, Showing showing) {
        showingsWithoutScreen.add(showing);
        showings.put(screen, showingsWithoutScreen);

        dataBase.updateExternalDB();
    }

    public void deleteShowing(Screen screen, String date, String time) {
        ArrayList<Showing> showingsByScreen = getScreenShowings(screen);
        Showing showing = getShowingByDateTime(screen, date, time);
        showingsByScreen.remove(showing);
        showings.put(screen, showingsByScreen);

        dataBase.updateExternalDB();
    }

    public Screen getScreenByNumber(Integer number) {
        for (Screen screen : showings.keySet()) {
            if (screen.getScreenNumber().equals(number)) {
                return screen;
            }
        }
        return null;
    }

    public Map<Screen, ArrayList<Showing>> getShowings() {
        return showings;
    }

    public void updateDB() {
        dataBase.updateExternalDB();
    }

}
