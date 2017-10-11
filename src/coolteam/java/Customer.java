package coolteam.java;

public class Customer extends User {

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void setLoggedOn(Boolean loggedOn) {
        this.loggedOn = loggedOn;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
