package cinemaclub.database;

import cinemaclub.user.Customer;

import java.io.Serializable;

public class ScreenRepository implements Serializable {

    private DataBase dataBase;

    ScreenRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public Boolean noExistingBooking(Customer customer) {
        return customer.getBookings().isEmpty();
    }


}
