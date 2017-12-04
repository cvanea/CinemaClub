package cinemaclub.database;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Screen;

import java.io.Serializable;
import java.util.Map;

public class ScreenRepository implements Serializable {

    private DataBase dataBase;
    private Map<String, Film> filmTimeSlots;
    private Map<Screen, Map<String, Film>> showings;

    ScreenRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public Map<String, Film> getScreenShowings(Screen screen) {
        return showings.get(screen);
    }

    public Film getFilmsByDate(Screen screen, String date) {
        return showings.get(screen).get(date);
    }

}
