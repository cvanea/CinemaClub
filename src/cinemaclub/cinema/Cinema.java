package cinemaclub.cinema;

import cinemaclub.user.Booking;
import cinemaclub.user.Customer;
import cinemaclub.user.User;
import cinemaclub.user.UserCredentials;
import exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Composed of cinema system classes, this class presents the api of the whole model.
 * Along with the systems it is composed of a map of screens and by default sets the current user to null.
 */
public class Cinema {
    //TODO change directory structure to be in line with what ghita wanted.

    private Login login;
    private Register register;
    private FilmDisplay filmDisplay;
    private FilmEdit filmEdit;
    private Profile profile;
    private BookingSystem bookingSystem;
    private Map<Integer, Screen> screens = new HashMap<>();
    private User currentUser = null;

    /**
     * At instantiation the cinema instantiates its composite classes and sets up its initial screens from the database.
     */
    public Cinema() {
        login = new Login();
        register = new Register();
        filmDisplay = new FilmDisplay();
        filmEdit = new FilmEdit();
        profile = new Profile();
        bookingSystem = new BookingSystem();
        setupScreensFromDB();
    }

    /**
     * Small helper method to fill the screens hashmap on the cinema from the database.
     */
    private void setupScreensFromDB() {
        ArrayList<Screen> allScreens = filmEdit.getScreens();
        for (Screen screen : allScreens) {
            screens.put(screen.getScreenNumber(), screen);
        }
    }

    /*
    UserId
     */

    /**
     * Simple getter for the staff ID map.
     *
     * @return the map of staff IDs to usernames
     */
    public Map<String, String> getStaffIDs() {
        return profile.getStaffIDs();
    }

    /**
     * Query to find a staff ID by username.
     *
     * @param username staff username
     * @return staff ID
     */
    public String getStaffIdByUsername(String username) {
        return profile.getStaffIdByUsername(username);
    }

    /**
     * Adds or updates an entry to the staff ID hashmap.
     *
     * @param id staff ID
     * @param username staff username
     */
    public void addStaffID(String id, String username) {
        profile.addStaffID(id, username);
    }

    /**
     * Removes an unassigned staff ID from available IDs.
     *
     * @param staffId staff ID
     * @throws StaffIdNotEmptyException prevents an assigned staff ID from being removed
     */
    public void deleteStaffId(String staffId) throws StaffIdNotEmptyException {
        profile.deleteStaffId(staffId);
    }

    /*
    Users
     */

    /**
     * Simple getter for current logged in user.
     * Returns null if there is no logged in user.
     *
     * @return logged in user.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets currentUser to null.
     */
    public void logoutCurrentUser() {
        currentUser = null;
    }

    /**
     * Query for a user by their username.
     *
     * @param username user username
     * @return user matching the username
     */
    public User getUser(String username) {
        return profile.getUser(username);
    }

    /**
     * Gets all registered users.
     *
     * @return all registered users
     */
    public ArrayList<User> getAllUsers() {
        return profile.getAllUsers();
    }

    /**
     * Attempts to log a user on by their username and password.
     * If successful, currentUser is set to the newly logged user.
     *
     * @param username user username
     * @param password user password
     * @throws UserDetailsDoNotExistException alerts user if that username does not exist
     * @throws UserDetailsIncorrectException alerts user if the password is incorrect
     */
    public void loginUser(String username, String password)
        throws UserDetailsDoNotExistException, UserDetailsIncorrectException {
        currentUser = login.loginUser(username, password);
    }

    /**
     * Attempts to register a new user.
     * User must input all details which later form their profile.
     *
     * @param username user username
     * @param email user email
     * @param password user password
     * @param firstName user's first name
     * @param surname user's surname
     * @param userType if user is a staff or customer
     * @param staffID required to register a new staff. Null for customers
     * @throws UsernameTakenException checks that username is unique
     * @throws IncorrectStaffIDException checks that the entered staff ID is valid
     * @throws StaffIDTakenException checks that entered staff ID isn't already assigned
     * @throws EmptyUserInputException checks that a user has filled in all fields before attempting to register
     * @throws NotValidEmailException checks that entered email takes the right format
     */
    public void registerUser(String username, String email, String password, String firstName, String surname, String userType, String staffID)
        throws UsernameTakenException, IncorrectStaffIDException, StaffIDTakenException, EmptyUserInputException, NotValidEmailException {
        register.registerUser(username, email, password, firstName, surname, userType, staffID);
    }

    /**
     * Gets the profile details of the current logged in user.
     *
     * @return class containing all profile details for a user
     */
    public UserCredentials getProfileDetails() {
        return profile.getProfileDetails(currentUser);
    }

    /**
     * Changes the current logged on user's username.
     *
     * @param newUsername new username
     * @throws UsernameTakenException checks that the new username doesn't already exist in the system
     */
    public void setUsername(String newUsername) throws UsernameTakenException {
        profile.setUsername(currentUser, newUsername);
    }

    /**
     * Changes a particular user's username.
     *
     * @param user user whose username changes
     * @param newUsername new username
     * @throws UsernameTakenException checks that the new username doesn't already exist in the system
     */
    public void setUsername(User user, String newUsername) throws UsernameTakenException {
        profile.setUsername(user, newUsername);
    }

    /**
     * Changes current user's email.
     *
     * @param newEmail new email
     */
    public void setUserEmail(String newEmail) {
        profile.setEmail(currentUser, newEmail);
    }

    /**
     * Changes current user's password.
     *
     * @param newPassword new password
     */
    public void setUserPassword(String newPassword) {
        profile.setPassword(currentUser, newPassword);
    }

    /**
     * Changes current user's first name.
     *
     * @param firstName new first name
     */
    public void setUserFirstName(String firstName) {
        profile.setFirstName(currentUser, firstName);
    }

    /**
     * Changes current user's surname.
     *
     * @param surname new surname
     */
    public void setUserSurname(String surname) {
        profile.setSurname(currentUser, surname);
    }

    /**
     * Deletes a user by their username from the database.
     *
     * @param username username to find the user
     */
    public void deleteUser(String username) {
        profile.deleteUser(username);
    }

    /*
    Bookings
     */

    /**
     * Gets all past and future bookings for a particular customer.
     *
     * @param customer customer who has bookings
     * @return booking history
     * @throws NoBookingsException alerts user that they don't have bookings
     */
    public ArrayList<Booking> getBookingsHistory(Customer customer) throws NoBookingsException {
        return profile.getBookingsHistory(customer);
    }

    /**
     * Gets all past bookings for a particular customer.
     *
     * @param customer customer who has bookings
     * @return past bookings history
     * @throws NoBookingsException alerts user that they don't have bookings
     * @throws NoPastBookingsException alerts user that they don't have past bookings
     */
    public ArrayList<Booking> getPastBookingsHistory(Customer customer) throws NoBookingsException, NoPastBookingsException {
        return profile.getPastBookingsHistory(customer);
    }

    /**
     * Gets all past bookings for current customer.
     *
     * @return past bookings history
     * @throws NoBookingsException alerts user that they don't have bookings
     * @throws NoPastBookingsException alerts user that they don't have past bookings
     */
    public ArrayList<Booking> getPastBookingsHistory() throws NoBookingsException, NoPastBookingsException {
        return profile.getPastBookingsHistory(currentUser);
    }

    /**
     * Gets all future bookings for a particular customer.
     *
     * @param customer customer who has bookings
     * @return future bookings history
     * @throws NoBookingsException alerts user that they don't have bookings
     * @throws NoFutureBookingsException alerts user that they don't have future bookings
     */
    public ArrayList<Booking> getFutureBookingsHistory(Customer customer) throws NoBookingsException, NoFutureBookingsException {
        return profile.getFutureBookingsHistory(customer);
    }

    /**
     * * Gets all future bookings for current customer.
     *
     * @return future bookings history
     * @throws NoBookingsException alerts user that they don't have bookings
     * @throws NoFutureBookingsException alerts user that they don't have future bookings
     */
    public ArrayList<Booking> getFutureBookingsHistory() throws NoBookingsException, NoFutureBookingsException {
        return profile.getFutureBookingsHistory(currentUser);
    }

    /**
     * @param booking
     * @throws NoBookingsException
     * @throws NotAFutureBookingException
     * @throws NoFutureBookingsException
     * @throws SeatNotFoundException
     */
    public void deleteFutureBooking(Booking booking)
        throws NoBookingsException, NotAFutureBookingException, NoFutureBookingsException, SeatNotFoundException {
        profile.deleteFutureBooking(currentUser, booking);
    }

    /**
     * @param customer
     * @param booking
     * @throws NoBookingsException
     * @throws NotAFutureBookingException
     * @throws NoFutureBookingsException
     * @throws SeatNotFoundException
     */
    public void deleteFutureBooking(Customer customer, Booking booking)
        throws NoBookingsException, NotAFutureBookingException, NoFutureBookingsException, SeatNotFoundException {
        profile.deleteFutureBooking(customer, booking);
    }

    /**
     * @param filmTitle
     * @return
     */
    public ArrayList<Booking> getBookingByTitle(String filmTitle) {
        if (currentUser instanceof Customer) {
            Customer customer = (Customer) currentUser;
            return customer.getBookingsByTitle(filmTitle);
        } else return null;
    }

    /**
     * @param filmTitle
     * @param date
     * @param time
     * @return
     */
    public Booking getBookingByTitleDateTime(String filmTitle, String date, String time) {
        if (currentUser instanceof Customer) {
            Customer customer = (Customer) currentUser;
            return customer.getBookingsByTitleDateTime(filmTitle, date, time);
        } else return null;
    }

    /**
     * @param showing
     * @param seatRow
     * @param seatNumber
     * @throws SeatAlreadyTakenException
     * @throws SeatNotFoundException
     */
    public void bookFilm(Showing showing, String seatRow, int seatNumber)
        throws SeatAlreadyTakenException, SeatNotFoundException {
        bookingSystem.bookFilm(currentUser, showing, seatRow, seatNumber);
    }

    /*
    Films
     */

    /**
     * @param title
     * @return
     */
    public Film getFilmByTitle(String title) {
        return filmEdit.getFilmDetailsByTitle(title);
    }

    /**
     * @param date
     * @return
     * @throws PastDateException
     */
    public ArrayList<Film> getFilmsByDate(String date) throws PastDateException {
        return filmDisplay.getFilmsByDate(date);
    }

    /**
     * @return
     */
    public ArrayList<Film> displayAllFilms() {
        return filmEdit.displayAllFilms();
    }

    /**
     * @param title
     * @param imagePath
     * @param description
     * @param runTime
     * @throws FilmExistsException
     */
    public void addFilm(String title, String imagePath, String description, String runTime) throws FilmExistsException {
        filmEdit.addFilm(title, imagePath, description, runTime);
    }

    /**
     * @param film
     * @param newTitle
     * @throws FilmExistsException
     */
    public void setFilmTitle(Film film, String newTitle) throws FilmExistsException {
        filmEdit.setFilmTitle(film, newTitle);
    }

    /**
     * @param film
     * @param newImagePath
     */
    public void setFilmImagePath(Film film, String newImagePath) {
        filmEdit.setFilmImagePath(film, newImagePath);
    }

    /**
     * @param film
     * @param newFilmDescription
     */
    public void setFilmDescription(Film film, String newFilmDescription) {
        filmEdit.setFilmDescription(film, newFilmDescription);
    }

    /**
     * @param film
     * @param newRunTime
     */
    public void setFilmRunTime(Film film, String newRunTime) {
        filmEdit.setFilmRunTime(film, newRunTime);
    }

    /**
     * @param title
     */
    public void deleteFilm(String title) {
        filmEdit.deleteFilm(title);
    }

    /*
    Showings
     */

    /**
     * @param date
     * @param screen
     * @return
     * @throws PastDateException
     */
    public ArrayList<Showing> getShowingsByDate(String date, Screen screen) throws PastDateException {
        return filmDisplay.getShowingsByDate(date, screen);
    }

    /**
     * @param date
     * @param time
     * @return
     */
    public Showing getShowingByDateTime(String date, String time) {
        return filmDisplay.getShowingByDateTime(date, time);
    }

    /**
     * @param date
     * @param time
     * @param film
     * @return
     */
    public Showing getShowingByDateTimeFilm(String date, String time, Film film) {
        return filmDisplay.getShowingByDateTimeFilm(date, time, film);
    }

    /**
     * @param screen
     * @param date
     * @param time
     * @return
     */
    public Showing getShowingByDateTimeScreen(Screen screen, String date, String time) {
        return filmDisplay.getShowingByDateTimeScreen(screen, date, time);
    }

    /**
     * @param film
     * @return
     */
    public ArrayList<String> getAllTimesByFilm(Film film) {
        return filmDisplay.getAllTimesByFilm(film);
    }

    /**
     * @param screen
     * @param film
     * @return
     */
    public ArrayList<String> getDatesByFilm(Screen screen, Film film) {
        return filmDisplay.getDatesByFilm(screen, film);
    }

    /**
     * @param film
     * @return
     */
    public ArrayList<Showing> getAllShowingsByFilm(Film film) {
        return filmDisplay.getAllShowingsByFilm(film);
    }

    /**
     * @return
     */
    public ArrayList<Showing> getAllShowings() {
        return filmDisplay.getAllShowings();
    }

    /**
     * @param screen
     * @param date
     * @param time
     * @param film
     * @throws ShowingAlreadyExistsException
     * @throws ShowingOnOtherScreenException
     * @throws OverlappingRuntimeException
     */
    public void addShowing(Screen screen, String date, String time, Film film) throws ShowingAlreadyExistsException, ShowingOnOtherScreenException, OverlappingRuntimeException {
        filmEdit.addShowing(screen, date, time, film);
    }

    /**
     * @param screen
     * @param date
     * @param time
     */
    public void deleteShowing(Screen screen, String date, String time) {
        filmEdit.deleteShowing(screen, date, time);
    }

    /**
     *
     */
    public void exportShowingsToCsv() {
        filmEdit.exportShowingsToCsv();
    }

    /*
    Screens
     */

    /**
     * @param screenNumber
     * @return
     */
    public Screen getScreen(Integer screenNumber) {
        return screens.get(screenNumber);
    }

    /**
     * @return
     */
    public ArrayList<Screen> getScreens() {
        ArrayList<Screen> allScreens = new ArrayList<>();
        allScreens.addAll(screens.values());
        return allScreens;
    }

    /**
     * @param screen
     * @throws ScreenNumberAlreadyExistsException
     */
    public void addScreen(Screen screen) throws ScreenNumberAlreadyExistsException {
        filmEdit.addScreen(screen);
        setupScreensFromDB();
    }

    /**
     * @param screen
     */
    public void deleteScreen(Screen screen) {
        filmEdit.deleteScreen(screen);
        screens.remove(screen.getScreenNumber());
    }
}
