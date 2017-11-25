package cinemaclub.cinema;

import cinemaclub.database.DataBase;

public class FilmEdit {

    private DataBase dataBase;

    FilmEdit() {
        this.dataBase = DataBase.getInstance();
    }
}

