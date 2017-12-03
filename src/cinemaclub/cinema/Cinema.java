package cinemaclub.cinema;

import cinemaclub.user.Booking;
import cinemaclub.user.Customer;
import cinemaclub.user.User;
import cinemaclub.user.UserCredentials;
import exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cinema {

    private Login login;
    private Register register;
    private FilmDisplay filmDisplay;
    private FilmEdit filmEdit;
    private Profile profile;
    private Map<Integer, Screen> screens;
    private User currentUser = null;

    public Cinema() {
        login = new Login();
        register = new Register();
        filmDisplay = new FilmDisplay();
        filmEdit = new FilmEdit();
        profile = new Profile();
        screens = setupScreens();
    }

    private Map<Integer, Screen> setupScreens() {
        Map<Integer, Screen> screensMap = new HashMap<>();
        screensMap.put(1, new Screen(5, 20));
        return screensMap;
    }

    public Screen getScreen(Integer screenNumber) {
        return screens.get(screenNumber);
    }

    public User getCurrentUser() {
        return currentUser;
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

    public void setUsername(String newUsername) throws UsernameTakenException {
        profile.setUsername(currentUser, newUsername);
    }

    public void setUserEmail(String newUsername) {
        profile.setEmail(currentUser, newUsername);
    }

    public void setUserPassword(String newUsername) {
        profile.setPassword(currentUser, newUsername);
    }

    public Film getFilm(String title) {
        return filmDisplay.getFilmDetails(title);
    }

    public ArrayList<Booking> getBookingHistory(Customer customer) throws NoBookingsException {
        return profile.getBookingHistory(customer);
    }

    public ArrayList<Film> displayFilms(LocalDate date) {
        return filmDisplay.displayFilms(date);
    }

    public void addFilm(String title, String imagePath, int runTime, String description) throws FilmExistsException {
        filmEdit.addFilm(title, imagePath, runTime, description);
    }

    public void setFilmTitle(Film film, String newTitle) throws FilmExistsException {
        filmEdit.setFilmTitle(film, newTitle);
    }

    public void setFilmImagePath(Film film, String newImagePath) {
        filmEdit.setFilmImagePath(film, newImagePath);
    }

    public void setFilmDecription(Film film, String newFilmDescription) {
        filmEdit.setFilmDecription(film, newFilmDescription);
    }

    public void setFilmRunTime(Film film, int newRunTime) {
        filmEdit.setFilmRunTime(film, newRunTime);
    }

    public void deleteFilm(String title) {
        filmEdit.deleteFilm(title);
    }

    public void deleteUser(String username) {
        profile.deleteUser(username);
    }
}
