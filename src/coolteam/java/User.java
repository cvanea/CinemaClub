package coolteam.java;

public abstract class User {

    Boolean loggedOn = false;
    String name;
    String password;
    String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedOn(Boolean loggedOn) {
        this.loggedOn = loggedOn;
    }
}