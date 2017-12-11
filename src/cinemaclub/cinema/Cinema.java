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
    private Map<Integer, Screen> screens = new HashMap<>();
    private User currentUser = null;

    public Cinema() {
        login = new Login();
        register = new Register();
        filmDisplay = new FilmDisplay();
        filmEdit = new FilmEdit();
        profile = new Profile();
        bookingSystem = new BookingSystem();
        setupScreensFromDB();
    }

    private void setupScreensFromDB() {
        ArrayList<Screen> allScreens = filmEdit.getScreens();

        for (Screen screen : allScreens) {
            screens.put(screen.getScreenNumber(), screen);
        }
    }

    public Screen getScreen(Integer screenNumber) {
        return screens.get(screenNumber);
    }

    public ArrayList<Screen> getScreens() {
        System.out.println(screens);
        ArrayList<Screen> allScreens = new ArrayList<>();
        allScreens.addAll(screens.values());
        return allScreens;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logoutCurrentUser() {
        currentUser = null;
    }

    public User getUser(String username) {
        return profile.getUser(username);
    }

    public void loginUser(String username, String password)
            throws UserDetailsDoNotExistException, UserDetailsIncorrectException {
        currentUser = login.loginUser(username, password);
    }

    public void registerUser(String username, String email, String password, String firstName, String surname, String userType, String staffID)
            throws UsernameTakenException, IncorrectStaffIDException, StaffIDTakenException, EmptyUserInputException {
        register.registerUser(username, email, password, firstName, surname, userType, staffID);
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

    public void setUserPassword(String newPassword) {
        profile.setPassword(currentUser, newPassword);
    }

    public void setUserFirstName(String firstName) {
        profile.setFirstName(currentUser, firstName);
    }

    public void setUserSurname(String surname) {
        profile.setSurname(currentUser, surname);
    }

    public Film getFilmByTitle(String title) {
        return filmEdit.getFilmDetailsByTitle(title);
    }

    public ArrayList<Booking> getBookingsHistory(Customer customer) throws NoBookingsException {
        return profile.getBookingsHistory(customer);
    }

    public ArrayList<Booking> getPastBookingsHistory(Customer customer) throws NoBookingsException, NoPastBookingsException {
        return profile.getPastBookingsHistory(customer);
    }

    public ArrayList<Booking> getPastBookingsHistory() throws NoBookingsException, NoPastBookingsException {
        return profile.getPastBookingsHistory(currentUser);
    }

    public ArrayList<Booking> getFutureBookingsHistory(Customer customer) throws NoBookingsException, NoFutureBookingsException {
        return profile.getFutureBookingsHistory(customer);
    }

    public ArrayList<Booking> getFutureBookingsHistory() throws NoBookingsException, NoFutureBookingsException {
        return profile.getFutureBookingsHistory(currentUser);
    }

    public void deleteFutureBooking(Booking booking)
            throws NoBookingsException, NotAFutureBookingException, NoFutureBookingsException, SeatNotFoundException {
        profile.deleteFutureBooking(currentUser, booking);
    }

    public void deleteFutureBooking(Customer customer, Booking booking)
            throws NoBookingsException, NotAFutureBookingException, NoFutureBookingsException, SeatNotFoundException {
        profile.deleteFutureBooking(customer, booking);
    }

    public ArrayList<Booking> getBookingByTitle(String filmTitle) {
        if (currentUser instanceof Customer) {
            Customer customer = (Customer) currentUser;
            return customer.getBookingsByTitle(filmTitle);
        } else return null;
    }

    public Booking getBookingByTitleDateTime(String filmTitle, String date, String time) {
        if (currentUser instanceof Customer) {
            Customer customer = (Customer) currentUser;
            return customer.getBookingsByTitleDateTime(filmTitle, date, time);
        } else return null;
    }

    public void bookFilm(Showing showing, String seatRow, int seatNumber)
            throws SeatAlreadyTakenException, SeatNotFoundException {
        bookingSystem.bookFilm(currentUser, showing, seatRow, seatNumber);
    }

    public ArrayList<Film> getFilmsByDate(String date) throws PastDateException {
        return filmDisplay.getFilmsByDate(date);
    }

    public ArrayList<Showing> getShowingsByDate(String date, Screen screen) throws PastDateException {
        return filmDisplay.getShowingsByDate(date, screen);
    }

    public Showing getShowingByDateTime(String date, String time) {
        return filmDisplay.getShowingByDateTime(date, time);
    }

    public Showing getShowingByDateTimeFilm(String date, String time, Film film) {
        return filmDisplay.getShowingByDateTimeFilm(date, time, film);
    }

    public Showing getShowingByDateTimeScreen(Screen screen, String date, String time) {
        return filmDisplay.getShowingByDateTimeScreen(screen, date, time);
    }

    public ArrayList<String> getAllTimesByFilm(Film film) {
        return filmDisplay.getAllTimesByFilm(film);
    }

    public ArrayList<String> getDatesByFilm(Screen screen, Film film) {
        return filmDisplay.getDatesByFilm(screen, film);
    }

    public ArrayList<Showing> getAllShowingsByFilm(Film film) {
        return filmDisplay.getAllShowingsByFilm(film);
    }

    public ArrayList<Showing> getAllShowings() {
        return filmDisplay.getAllShowings();
    }

    public void addShowing(Screen screen, String date, String time, Film film) throws ShowingAlreadyExistsException, ShowingOnOtherScreenException, OverlappingRuntimeException {
        filmEdit.addShowing(screen, date, time, film);
    }

    public void deleteShowing(Screen screen, String date, String time) {
        filmEdit.deleteShowing(screen, date, time);
    }

    public void exportShowingsToCsv() {
        filmEdit.exportShowingsToCsv();
    }

    public ArrayList<Film> displayAllFilms() {
        return filmEdit.displayAllFilms();
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

    public void addScreen(Screen screen) throws ScreenNumberAlreadyExistsException {
        filmEdit.addScreen(screen);
        setupScreensFromDB();
    }

    public void deleteFilm(String title) {
        filmEdit.deleteFilm(title);
    }

    public void deleteUser(String username) {
        profile.deleteUser(username);
    }
}
