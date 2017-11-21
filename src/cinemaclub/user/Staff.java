package cinemaclub.user;

public class Staff extends User {

    int employeeID = 10;

    public Staff(Boolean loggedOn, String name, String email, String password) {
        this.loggedOn = loggedOn;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String IExist() {
        return "I exist as a staff!";
    }

}
