package cinemaclub.user;

import cinemaclub.database.UserCredentials;

public class Staff extends User {

    public Staff(UserCredentials userCredentials) {
        super(userCredentials);
        this.loggedOn = true;
    }

    public String IExist() {
        return "I exist as a staff!";
    }

}
