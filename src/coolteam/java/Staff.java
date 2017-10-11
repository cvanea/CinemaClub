package coolteam.java;

public class Staff extends User {

    int employeeID;

    public Staff(String name, String email, int employeeID){
        this.name = name;
        this.email = email;
        this.employeeID = employeeID;
    }


    public void setLoggedOn(boolean loggedOn) {
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
