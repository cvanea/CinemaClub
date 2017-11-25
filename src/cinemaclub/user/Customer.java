package cinemaclub.user;

public class Customer extends cinemaclub.user.User {

    public Customer(UserCredentials userCredentials) {
        super(userCredentials);
        this.loggedOn = true;
    }

    public String IExist() {
        return "I exist as a customer!";
    }

}
