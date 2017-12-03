package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.FilmRepository;

import java.time.LocalDate;
import java.util.ArrayList;

class FilmDisplay {
    private FilmRepository filmRepository;

    FilmDisplay() {
        this.filmRepository = DataBase.getFilmRepository();
    }

    ArrayList<Film> displayFilms(LocalDate date) {

        return new ArrayList<>();
    }

//new Film("UP", "Path", "Film about man boy and a dog", 1)

    Film getFilmDetails(String title) {
        return filmRepository.getFilm(title);
    }

}