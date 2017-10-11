package coolteam.java;

public abstract class User {

    Boolean loggedOn = false;
    String name;
    String password;
    String email;

    public abstract String getPassword();

    public abstract void setPassword(String password);

}
