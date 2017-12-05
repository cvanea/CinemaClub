package cinemaclub.user;

import java.io.Serializable;

public class UserCredentials implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String surname;

    public UserCredentials(String username, String email, String password, String firstName, String surname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean checkCredentials(UserCredentials credentialsToValidate) {

        return this.username.equals(credentialsToValidate.getUsername()) &&
            this.password.equals(credentialsToValidate.getPassword()) &&
            this.email.equals(credentialsToValidate.getEmail()) &&
            this.firstName.equals(credentialsToValidate.getFirstName()) &&
            this.surname.equals(credentialsToValidate.getSurname());
    }

    public String toString() {
        return this.getUsername() + ", " + this.getEmail() + ", " + this.getPassword();
    }
}
