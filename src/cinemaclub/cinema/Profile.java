package cinemaclub.cinema;

import cinemaclub.database.DataBase;

public class Profile {

    private DataBase dataBase;

    Profile() {
        this.dataBase = DataBase.getInstance();
    }
}
