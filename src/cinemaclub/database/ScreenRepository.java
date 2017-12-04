package cinemaclub.database;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Screen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScreenRepository implements Serializable {

    private DataBase dataBase;
    private Map<Screen, Map<String, Map<String, Film>>> showings;

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

    public void addShowing(Screen screen, String date, String time, Film film) {
        Map<String, Film> filmTimes = new HashMap<>();
        filmTimes.put(time, film);

        Map<String, Map<String, Film>> filmDateTimes = new HashMap<>();
        filmDateTimes.put(date, filmTimes);

        showings.put(screen, filmDateTimes);

        dataBase.updateExternalDB();
    }

    public void deleteShowing(Screen screen, String date, String time) {
        Map<String, Film> filmTimes = this.getShowingsByDate(screen, date);
        filmTimes.remove(time);

        dataBase.updateExternalDB();

    }

}
