package cinemaclub.database;

import java.io.Serializable;

public class ScreenRepository implements Serializable {

    private DataBase dataBase;

    ScreenRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }


}
