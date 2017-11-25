package cinemaclub.user;

public class Staff extends cinemaclub.user.User {

    public Staff(UserCredentials userCredentials) {
        super(userCredentials);
    }

    public String IExist() {
        return "I exist as a staff!";
    }

}
