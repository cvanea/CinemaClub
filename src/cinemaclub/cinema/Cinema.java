package cinemaclub.cinema;

import cinemaclub.user.User;
import exceptions.IncorrectStaffIDException;
import exceptions.StaffIDTakenException;
import exceptions.UserDetailsDoNotExistException;
import exceptions.UsernameTakenException;

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

    public void registerUser(String username, String email, String password, String userType, String staffID)
        throws UsernameTakenException, IncorrectStaffIDException, StaffIDTakenException {
        register.registerUser(username, email, password, userType, staffID);
    }

}
