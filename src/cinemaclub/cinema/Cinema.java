package cinemaclub.cinema;

import cinemaclub.user.User;
import exceptions.UserDetailsDoNotExistException;

public class Cinema {

    private Login login;
    private Register register;
    private FilmDisplay filmDisplay;
    private FilmEdit filmEdit;
    private Profile profile;

    public Cinema() {
        login = new Login();
        register = new Register();
        filmDisplay = new FilmDisplay();
        filmEdit = new FilmEdit();
        profile = new Profile();
    }

    public User loginUser(String username, String email, String password, String userType)
        throws UserDetailsDoNotExistException {
        return login.loginUser(username, email, password, userType);
    }

    public void registerUser() {
        register.registerUser();
    }

}
