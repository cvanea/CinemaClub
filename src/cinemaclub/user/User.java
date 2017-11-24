package cinemaclub.user;

public abstract class User {

    Boolean loggedOn = false;
    String name;
    String password;
    String email;

    abstract public String IExist();

    public Boolean getLoggedOn() {
        return loggedOn;
    }

    public String getName() {
        return name;
    }
}