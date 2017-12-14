package cinemaclub.user;

import java.io.Serializable;

/**
 * Small helper class which holds all user details.
 * Serializable allows it to be saved as bytes to an external database file.
 */
public class UserCredentials implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String surname;

    /**
     * Instantiating userCredentials requires all userCredential properties to be passed.
     *
     * @param username username
     * @param email email
     * @param password password
     * @param firstName first name
     * @param surname surname
     */
    public UserCredentials(String username, String email, String password, String firstName, String surname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
    }

    /**
     * Gets the password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the first name.
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the surname.
     *
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname.
     *
     * @param surname surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Overrides toString method to clearly show all userCredential properties.
     *
     * @return string of all userCredential properties
     */
    @Override
    public String toString() {
        return this.getUsername() + ", " + this.getEmail() + ", " + this.getPassword();
    }
}
