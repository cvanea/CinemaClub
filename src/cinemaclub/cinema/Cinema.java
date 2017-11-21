package cinemaclub.cinema;

import cinemaclub.user.User;

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

    public User loginUser() {
        return login.loginUser();
    }


}
