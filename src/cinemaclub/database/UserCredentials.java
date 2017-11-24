package cinemaclub.database;

public class UserCredentials {

    private String userName;
    private String email;
    private String password;
    private String userType;

    public UserCredentials(String userName, String email, String password, String userType) {
        this.userName = userName;
        this.userType = userType;
        this.password = password;
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean checkCredentials(UserCredentials credentialsToValidate) {

        return this.userName.equals(credentialsToValidate.getUserName()) &&
            this.userType.equals(credentialsToValidate.getUserType()) &&
            this.password.equals(credentialsToValidate.getPassword()) &&
            this.email.equals(credentialsToValidate.getEmail());
    }
}
