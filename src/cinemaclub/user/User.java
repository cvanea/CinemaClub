package cinemaclub.user;

import cinemaclub.database.UserCredentials;

public abstract class User {

    Boolean loggedOn = false;
    private UserCredentials userCredentials;

    User(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    abstract public String IExist();

    public Boolean getLoggedOn() {
        return loggedOn;
    }

    public String getName() {
        return userCredentials.getUserName();
    }

    public String getEmail() {
        return userCredentials.getEmail();
    }

    public String getPassword() {
        return userCredentials.getPassword();
    }

    public void setPassword(String password) {
        userCredentials.setPassword(password);
    }

    public void setUserName(String userName) {
        userCredentials.setUserName(userName);
    }

    public void setEmail(String email) {
        userCredentials.setEmail(email);
    }

}