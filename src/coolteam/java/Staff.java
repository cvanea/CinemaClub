package coolteam.java;

public class Staff extends User {

    int employeeID = 10;

    public Staff(String name, String email) {
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
