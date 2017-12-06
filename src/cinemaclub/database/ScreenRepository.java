package cinemaclub.database;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Screen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScreenRepository implements Serializable {

    private DataBase dataBase;
    private Map<String, Film> filmTimes = new HashMap<>();
    private Map<String, Map<String, Film>> filmDateTimes = new HashMap<>();
    private Map<Screen, Map<String, Map<String, Film>>> showings = new HashMap<>();

    ScreenRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public Map<String, Map<String, Film>> getScreenShowings(Screen screen) {
        return showings.get(screen);
    }

    public ArrayList<Film> getFilmsByDate(Screen screen, String date) {
        Map<String, Map<String, Film>> filmDatesTimes = showings.get(screen);
        Map<String, Film> filmTimes = filmDatesTimes.get(date);

        return new ArrayList<>(filmTimes.values());
    }

    public Map<String, Film> getShowingsByDate(Screen screen, String date) {
        Map<String, Map<String, Film>> filmDatesTimes = showings.get(screen);

        return filmDatesTimes.get(date);
    }

    public ArrayList<String> getTimesByFilm(Film film) {
        ArrayList<String> times = new ArrayList<>();

        for (String time : filmTimes.keySet()) {
            if (filmTimes.get(time) == film) {
                times.add(time);
            }
        }
        return times;
    }

    public void addShowing(Screen screen, String date, String time, Film film) {
        filmTimes.put(time, film);
        filmDateTimes.put(date, filmTimes);
        showings.put(screen, filmDateTimes);

        dataBase.updateExternalDB();
    }

    public void deleteShowing(Screen screen, String date, String time) {
        Map<String, Film> filmTimes = this.getShowingsByDate(screen, date);
        filmTimes.remove(time);

        dataBase.updateExternalDB();
    }

    public Screen getScreenByNumber(Integer number) {
        for (Screen screen : showings.keySet()) {
            if (screen.getScreenNumber() == number) {
                System.out.println("if reached");
                return screen;
            }
        }
        return null;
    }

    public void updateDB() {
        dataBase.updateExternalDB();
    }

}
