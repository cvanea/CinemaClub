package cinemaclub.cinema;

import cinemaclub.database.DataBase;

public class FilmDisplay {

    private DataBase dataBase;

    FilmDisplay() {
        this.dataBase = DataBase.getInstance();
    }
}
