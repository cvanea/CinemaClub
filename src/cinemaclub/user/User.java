package cinemaclub.user;

import java.io.Serializable;

/**
 *
 */
public abstract class User implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private UserCredentials userCredentials;

    /**
     * @param userCredentials
     */
    User(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public String getUsername() {
        return userCredentials.getUsername();
    }

    public String getEmail() {
        return userCredentials.getEmail();
    }

    public String getPassword() {
        return userCredentials.getPassword();
    }

    public String getFirstName() {
        return userCredentials.getFirstName();
    }

    public String getSurname() {
        return userCredentials.getSurname();
    }

    public void setPassword(String password) {
        userCredentials.setPassword(password);
    }

    public void setUsername(String userName) {
        userCredentials.setUsername(userName);
    }

    public void setEmail(String email) {
        userCredentials.setEmail(email);
    }

    public void setFirstName(String firstName) {
        userCredentials.setFirstName(firstName);
    }

    public void setSurname(String surname) {
        userCredentials.setSurname(surname);
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    @Override
    public String toString() {
        return userCredentials.toString();
    }
}