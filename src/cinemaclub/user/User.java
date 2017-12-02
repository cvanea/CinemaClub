package cinemaclub.user;

import java.io.Serializable;

public abstract class User implements Serializable {

    private UserCredentials userCredentials;

    User(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    abstract public String IExist();

    public String getUsername() {
        return userCredentials.getUsername();
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

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    @Override
    public String toString() {
        return userCredentials.toString();
    }
}