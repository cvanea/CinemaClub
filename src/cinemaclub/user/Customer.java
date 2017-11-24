package cinemaclub.user;

import cinemaclub.database.UserCredentials;

public class Customer extends User {

    public Customer(UserCredentials userCredentials) {
        super(userCredentials);
        this.loggedOn = true;
    }

    public String IExist() {
        return "I exist as a customer!";
    }

}
