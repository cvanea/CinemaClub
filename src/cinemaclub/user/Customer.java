package cinemaclub.user;

public class Customer extends User {

    public Customer(Boolean loggedOn, String name, String email, String password) {
        this.loggedOn = loggedOn;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String IExist() {
        return "I exist as a customer!";
    }

}
