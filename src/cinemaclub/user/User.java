package cinemaclub.user;

public abstract class User {

    Boolean loggedOn = false;
    String name;
    String password;
    String email;

    public String IExist() {
        return "I exist!";
    }

}