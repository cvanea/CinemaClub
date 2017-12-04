package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.FilmRepository;
import cinemaclub.database.ScreenRepository;

import java.util.ArrayList;

class FilmDisplay {
    private FilmRepository filmRepository;
    private ScreenRepository screenRepository;

    FilmDisplay() {
        this.filmRepository = DataBase.getFilmRepository();
        this.screenRepository = DataBase.getScreenRepository();
    }

    ArrayList<Film> displayFilms(String date, Screen screen) {

        ArrayList<Film> allFilms = displayAllFilms();

        screenRepository.getFilmsByDate(screen, date);

        return new ArrayList<>();
    }

    ArrayList<Film> displayAllFilms() {

        return new ArrayList<>(filmRepository.getAllFilms());
    }

    ArrayList<Film> displayAllFilms(Screen screen) {

        return new ArrayList<>(filmRepository.getAllFilms());
    }

    Film getFilmDetails(String title) {
        return filmRepository.getFilm(title);
    }

}