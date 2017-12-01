package cinemaclub.cinema;

import cinemaclub.database.DataBase;

import java.time.LocalDate;
import java.util.ArrayList;

class FilmDisplay {

    private DataBase dataBase;

    FilmDisplay() {
        this.dataBase = DataBase.getInstance();
    }

    ArrayList<Film> displayFilms(LocalDate date) {

        return new ArrayList<>();
    }

//new Film("UP", "Path", "Film about man boy and a dog", 1)

    Film getFilmDetails(String title) {
        return dataBase.getFilm(title);
    }

}