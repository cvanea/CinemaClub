package coolteam.java;

public class Staff extends User {

    int employeeCode;

    public Staff(String name, String email, int employeeCode) {
        this.name = name;
        this.email = email;
        this.employeeCode = employeeCode;
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
