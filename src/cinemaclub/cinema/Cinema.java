package cinemaclub.cinema;

import cinemaclub.user.Booking;
import cinemaclub.user.Customer;
import cinemaclub.user.User;
import cinemaclub.user.UserCredentials;
import exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cinema {

    private Login login;
    private Register register;
    private FilmDisplay filmDisplay;
    private FilmEdit filmEdit;
    private Profile profile;
    private BookingSystem bookingSystem;
    private Map<Integer, Screen> screens;
    private ArrayList<Film> showings;
    private User currentUser = null;

    public Cinema() {
        login = new Login();
        register = new Register();
        filmDisplay = new FilmDisplay();
        filmEdit = new FilmEdit();
        profile = new Profile();
        bookingSystem = new BookingSystem();
        screens = setupScreens();
    }

    //Sets up the number of screens in the cinema and their seat number
    private Map<Integer, Screen> setupScreens() {
        Map<Integer, Screen> screensMap = new HashMap<>();
        screensMap.put(1, new Screen(1, 5, 10));
        return screensMap;
    }

    public Screen getScreen(Integer screenNumber) {
        return screens.get(screenNumber);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logoutCurrentUser() {
        currentUser = null;
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

    public ArrayList<Booking> getBookingsHistory(Customer customer) throws NoBookingsException {
        return profile.getBookingsHistory(customer);
    }

    public ArrayList<Booking> getPastBookingsHistory(Customer customer) throws NoBookingsException {
        return profile.getPastBookingsHistory(customer);
    }

    public ArrayList<Booking> getPastBookingsHistory() throws NoBookingsException {
        return profile.getPastBookingsHistory(currentUser);
    }

    public ArrayList<Booking> getFutureBookingsHistory(Customer customer) throws NoBookingsException, NoFutureBookingsException {
        return profile.getFutureBookingsHistory(customer);
    }

    public ArrayList<Booking> getFutureBookingsHistory() throws NoBookingsException, NoFutureBookingsException {
        return profile.getFutureBookingsHistory(currentUser);
    }

    public void deleteFutureBooking(Booking booking)
        throws NoBookingsException, NotAFutureBookingException, NoFutureBookingsException {
        profile.deleteFutureBooking(currentUser, booking);
    }

    public void deleteFutureBooking(Customer customer, Booking booking)
        throws NoBookingsException, NotAFutureBookingException, NoFutureBookingsException {
        profile.deleteFutureBooking(customer, booking);
    }

    public Booking getBookingByTitle(String filmTitle) {
        if (currentUser instanceof Customer) {
            Customer customer = (Customer) currentUser;
            return customer.getBookingByTitle(filmTitle);
        } else return null;
    }

    public void bookFilm(String date, String time, Film film, Screen screen, String seatRow, int seatNumber)
        throws SeatAlreadyTakenException, SeatNotFoundException {
        bookingSystem.bookFilm(currentUser, date, time, film, screen, seatRow, seatNumber);
    }

    public ArrayList<Film> displayFilms(String date) {
        return filmDisplay.displayFilms(date);
    }

    public void addFilm(String title, String imagePath, String description, String runTime) throws FilmExistsException {
        filmEdit.addFilm(title, imagePath, description, runTime);
    }

    public void setFilmTitle(Film film, String newTitle) throws FilmExistsException {
        filmEdit.setFilmTitle(film, newTitle);
    }

    public void setFilmImagePath(Film film, String newImagePath) {
        filmEdit.setFilmImagePath(film, newImagePath);
    }

    public void setFilmDescription(Film film, String newFilmDescription) {
        filmEdit.setFilmDescription(film, newFilmDescription);
    }

    public void setFilmRunTime(Film film, String newRunTime) {
        filmEdit.setFilmRunTime(film, newRunTime);
    }

    public void deleteFilm(String title) {
        filmEdit.deleteFilm(title);
    }

    public void deleteUser(String username) {
        profile.deleteUser(username);
    }
}
