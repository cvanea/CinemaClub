package cinemaclub.user;

public abstract class User {

    private UserCredentials userCredentials;

    User(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    abstract public String IExist();

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

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

}