package cinemaclub.cinema;

import cinemaclub.user.User;
import cinemaclub.user.UserCredentials;
import exceptions.*;

public class Cinema {

    private Login login;
    private Register register;
    private FilmDisplay filmDisplay;
    private FilmEdit filmEdit;
    private Profile profile;
    private User currentUser = null;

    public Cinema() {
        login = new Login();
        register = new Register();
        filmDisplay = new FilmDisplay();
        filmEdit = new FilmEdit();
        profile = new Profile();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void loginUser(String username, String email, String password)
        throws UserDetailsDoNotExistException, UserDetailsIncorrectException {
        currentUser = login.loginUser(username, email, password);
    }

    public void registerUser(String username, String email, String password, String userType, String staffID)
        throws UsernameTakenException, IncorrectStaffIDException, StaffIDTakenException {
        register.registerUser(username, email, password, userType, staffID);
    }

    public UserCredentials getProfileDetails() {
        return profile.getProfileDetails(currentUser);
    }

    public void setUsername(String newUsername) {
        profile.setUsername(currentUser, newUsername);
    }

    public void setUserEmail(String newUsername) {
        profile.setEmail(currentUser, newUsername);
    }

    public void setUserPassword(String newUsername) {
        profile.setPassword(currentUser, newUsername);
    }

}
