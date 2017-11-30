package cinemaclub.user;

public class UserCredentials {

    private String userName;
    private String email;
    private String password;

    public UserCredentials(String userName, String email, String password) {
        this.userName = userName;
        this.password = password;
        this.email = email;
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
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean checkCredentials(UserCredentials credentialsToValidate) {

        return this.userName.equals(credentialsToValidate.getUsername()) &&
            this.password.equals(credentialsToValidate.getPassword()) &&
            this.email.equals(credentialsToValidate.getEmail());
    }

    public String toString() {
        return this.getUsername() + ", " + this.getEmail() + ", " + this.getPassword();
    }
}
