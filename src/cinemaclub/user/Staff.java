package cinemaclub.user;

public class Staff extends User {

    public Staff(String name, String email, String password) {
        this.loggedOn = true;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String IExist() {
        return "I exist as a staff!";
    }

}
