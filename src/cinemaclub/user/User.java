package cinemaclub.user;

import java.io.Serializable;

/**
 * Abstract class for users of the cinema system.
 * Serializable allows it to be saved as bytes to an external database file.
 */
public abstract class User implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private UserCredentials userCredentials;

    /**
     * Subclasses of User must have UserCredentials at instantiation.
     *
     * @param userCredentials profile details such as username, first name, email, etc.
     */
    User(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    /**
     * Gets the username from userCredentials.
     *
     * @return username
     */
    public String getUsername() {
        return userCredentials.getUsername();
    }

    /**
     * Gets the email from userCredentials.
     *
     * @return email
     */
    public String getEmail() {
        return userCredentials.getEmail();
    }

    /**
     * Gets the password from userCredentials.
     *
     * @return password
     */
    public String getPassword() {
        return userCredentials.getPassword();
    }

    /**
     * Gets the first name from userCredentials.
     *
     * @return first name
     */
    public String getFirstName() {
        return userCredentials.getFirstName();
    }

    /**
     * Gets the surname from userCredentials.
     *
     * @return surname
     */
    public String getSurname() {
        return userCredentials.getSurname();
    }

    /**
     * Sets the username from userCredentials.
     *
     * @param userName username
     */
    public void setUsername(String userName) {
        userCredentials.setUsername(userName);
    }

    /**
     * Sets the email from userCredentials.
     *
     * @param email email
     */
    public void setEmail(String email) {
        userCredentials.setEmail(email);
    }

    /**
     * Sets the password from userCredentials.
     *
     * @param password password
     */
    public void setPassword(String password) {
        userCredentials.setPassword(password);
    }

    /**
     * Sets the first name from userCredentials.
     *
     * @param firstName first name
     */
    public void setFirstName(String firstName) {
        userCredentials.setFirstName(firstName);
    }

    /**
     * Sets the surname from userCredentials.
     *
     * @param surname surname
     */
    public void setSurname(String surname) {
        userCredentials.setSurname(surname);
    }

    /**
     * Gets all userCredentials.
     *
     * @return userCredentials
     */
    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    /**
     * Overrides toString method to clearly show all user properties.
     * This takes the toString method from the userCredentials on the user.
     *
     * @return string of all user properties
     */
    @Override
    public String toString() {
        return userCredentials.toString();
    }
}