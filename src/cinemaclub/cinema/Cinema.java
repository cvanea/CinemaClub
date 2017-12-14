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
 * It is designed to be usable no matter the kind of interaction; be that javafx or cli.
 * Along with the cinema systems it is composed of a map of screens and by default has no current user logged on.
 * Most methods either perform a system function, such as registering, get or set, or query the database.
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
     * Small helper method to fill the cinema screens hashmap on instantiation from the database.
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
     * Getter for the staff ID map.
     *
     * @return the map of staff IDs to usernames
     */
    public Map<String, String> getStaffIDs() {
        return profile.getStaffIDs();
    }

    /**
     * Gets staff ID by username.
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
     * Attempts to add a booking for current user.
     *
     * @param showing the showing being booked
     * @param seatRow the seat row being booked
     * @param seatNumber the seat number being booked
     * @throws SeatAlreadyTakenException prevents the user from booking an already booked seat
     * @throws SeatNotFoundException prevents the user from booking a non-existing seat
     */
    public void bookFilm(Showing showing, String seatRow, int seatNumber)
        throws SeatAlreadyTakenException, SeatNotFoundException {
        bookingSystem.bookFilm(currentUser, showing, seatRow, seatNumber);
    }

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
     * Gets all future bookings for current customer.
     *
     * @return future bookings history
     * @throws NoBookingsException alerts user that they don't have bookings
     * @throws NoFutureBookingsException alerts user that they don't have future bookings
     */
    public ArrayList<Booking> getFutureBookingsHistory() throws NoBookingsException, NoFutureBookingsException {
        return profile.getFutureBookingsHistory(currentUser);
    }

    /**
     * Get all bookings of a particular film by its title.
     *
     * @param filmTitle title of the film booking
     * @return bookings of a particular film
     */
    public ArrayList<Booking> getBookingByTitle(String filmTitle) {
        if (currentUser instanceof Customer) {
            Customer customer = (Customer) currentUser;
            return customer.getBookingsByTitle(filmTitle);
        } else return null;
    }

    /**
     * Get a booking on current user by its film title, the date, and time.
     * Bookings are held on customers so first current user must be checked for being a customer.
     *
     * @param filmTitle film title of booking
     * @param date date of booking
     * @param time time of booking
     * @return the matched booking
     */
    public Booking getBookingByTitleDateTime(String filmTitle, String date, String time) {
        if (currentUser instanceof Customer) {
            Customer customer = (Customer) currentUser;
            return customer.getBookingsByTitleDateTime(filmTitle, date, time);
        } else return null;
    }

    /**
     * Deletes a particular booking for the current user.
     *
     * @param booking booking to be deleted
     * @throws NoBookingsException alerts user that they don't have bookings
     * @throws NotAFutureBookingException alerts user that the booking they are trying to delete isn't future
     * @throws NoFutureBookingsException alerts user that they don't have future bookings
     * @throws SeatNotFoundException a booking requires a seat booked to be deleted. This is thrown if the seat doesn't exist.
     */
    public void deleteFutureBooking(Booking booking)
        throws NoBookingsException, NotAFutureBookingException, NoFutureBookingsException, SeatNotFoundException {
        profile.deleteFutureBooking(currentUser, booking);
    }

    /**
     * Deletes a particular booking for a particular user.
     *
     * @param customer customer on which to delete the booking
     * @param booking booking to be deleted
     * @throws NoBookingsException alerts user that they don't have bookings
     * @throws NotAFutureBookingException alerts user that the booking they are trying to delete isn't future
     * @throws NoFutureBookingsException alerts user that they don't have future bookings
     * @throws SeatNotFoundException a booking requires a seat booked to be deleted. This is thrown if the seat doesn't exist.
     */
    public void deleteFutureBooking(Customer customer, Booking booking)
        throws NoBookingsException, NotAFutureBookingException, NoFutureBookingsException, SeatNotFoundException {
        profile.deleteFutureBooking(customer, booking);
    }

    /*
    Films
     */

    /**
     * Attempts to add a film to the cinema film database.
     *
     * @param title new film's title
     * @param imagePath new film's image path
     * @param description new film's description
     * @param runTime new film's runtime
     * @throws FilmExistsException prevents a film from being added if there is already one with the same title
     */
    public void addFilm(String title, String imagePath, String description, String runTime) throws FilmExistsException {
        filmEdit.addFilm(title, imagePath, description, runTime);
    }

    /**
     * Changes a film's title.
     *
     * @param film the file whose title is to be changed
     * @param newTitle the new title
     * @throws FilmExistsException prevents the new title from being the same as an existing title
     */
    public void setFilmTitle(Film film, String newTitle) throws FilmExistsException {
        filmEdit.setFilmTitle(film, newTitle);
    }

    /**
     * Changes a film's image path.
     *
     * @param film the film whose path is to be changed
     * @param newImagePath the new image path
     */
    public void setFilmImagePath(Film film, String newImagePath) {
        filmEdit.setFilmImagePath(film, newImagePath);
    }

    /**
     * Changes a film's description.
     *
     * @param film the film whose description is to be changed
     * @param newFilmDescription new film description
     */
    public void setFilmDescription(Film film, String newFilmDescription) {
        filmEdit.setFilmDescription(film, newFilmDescription);
    }

    /**
     * Changes a film's runtime.
     *
     * @param film the film whose description is to be changed
     * @param newRunTime new film runtime
     */
    public void setFilmRunTime(Film film, String newRunTime) {
        filmEdit.setFilmRunTime(film, newRunTime);
    }

    /**
     * Gets all films that could be shown at the cinema.
     *
     * @return all films in the film database
     */
    public ArrayList<Film> displayAllFilms() {
        return filmEdit.displayAllFilms();
    }

    /**
     * Gets film with the film title.
     *
     * @param title title of film
     * @return film object matching title
     */
    public Film getFilmByTitle(String title) {
        return filmEdit.getFilmDetailsByTitle(title);
    }

    /**
     * Removes a film from the film database.
     *
     * @param title title of film to be deleted
     */
    public void deleteFilm(String title) {
        filmEdit.deleteFilm(title);
    }

    /*
    Showings
     */

    /**
     * Attempts to add a showing to the screens database.
     *
     * @param screen screen on which the showing is added
     * @param date date of the showing
     * @param time time of the showing
     * @param film film of the showing
     * @throws ShowingAlreadyExistsException prevents adding a showing that already exists
     * @throws ShowingOnOtherScreenException prevents a showing from occurring at the same time and date across screens
     * @throws OverlappingRuntimeException prevents adding a showing when it would overlap with an existing showing
     */
    public void addShowing(Screen screen, String date, String time, Film film) throws ShowingAlreadyExistsException, ShowingOnOtherScreenException, OverlappingRuntimeException {
        filmEdit.addShowing(screen, date, time, film);
    }

    /**
     * Exports all showings, with extra details, to a csv file.
     */
    public void exportShowingsToCsv() {
        filmEdit.exportShowingsToCsv();
    }

    /**
     * Gets all films that are showing on a particular date.
     *
     * @param date date of film showings
     * @return all films showing on the date
     * @throws PastDateException only allows future showings to get returned
     */
    public ArrayList<Film> getFilmsByDate(String date) throws PastDateException {
        return filmDisplay.getFilmsByDate(date);
    }

    /**
     * Get showings that show on a certain date and screen.
     *
     * @param date date of showings
     * @param screen screen of showings
     * @return showings matching the date and screen
     * @throws PastDateException prevents past showings from being matched
     */
    public ArrayList<Showing> getShowingsByDate(String date, Screen screen) throws PastDateException {
        return filmDisplay.getShowingsByDate(date, screen);
    }

    /**
     * Get showing by the date, time, and film of the showing.
     * Will always be unique since, by design, showings cannot be the same across screen.
     *
     * @param date date of showing
     * @param time time of showing
     * @param film film of showing
     * @return the showing matching the date, time, and film
     */
    public Showing getShowingByDateTimeFilm(String date, String time, Film film) {
        return filmDisplay.getShowingByDateTimeFilm(date, time, film);
    }

    /**
     * Get showing by the date, time, and screen of the showing.
     *
     * @param screen screen of showing
     * @param date date of showing
     * @param time time of showing
     * @return showing matching the screen, date, and time
     */
    public Showing getShowingByDateTimeScreen(Screen screen, String date, String time) {
        return filmDisplay.getShowingByDateTimeScreen(screen, date, time);
    }

    /**
     * Get all film times for a particular film.
     *
     * @param film film searching by
     * @return all times for that string
     */
    public ArrayList<String> getAllTimesByFilm(Film film) {
        return filmDisplay.getAllTimesByFilm(film);
    }

    /**
     * Get film dates times for a particular film on a screen.
     *
     * @param screen screen of showing
     * @param film film showing
     * @return dates matching the screen and film
     */
    public ArrayList<String> getDatesByFilm(Screen screen, Film film) {
        return filmDisplay.getDatesByFilm(screen, film);
    }

    /**
     * Gets all showings of a particular film.
     *
     * @param film film of the showings
     * @return all showings of the film
     */
    public ArrayList<Showing> getAllShowingsByFilm(Film film) {
        return filmDisplay.getAllShowingsByFilm(film);
    }

    /**
     * Gets all showings in the screens database.
     *
     * @return all showings
     */
    public ArrayList<Showing> getAllShowings() {
        return filmDisplay.getAllShowings();
    }

    /**
     * Removes a showing from the screens database.
     * Uniquely picks out a showing.
     *
     * @param screen screen of the showing
     * @param date date of the showing
     * @param time time of the showing
     */
    public void deleteShowing(Screen screen, String date, String time) {
        filmEdit.deleteShowing(screen, date, time);
    }

    /*
    Screens
     */

    /**
     * Attempts to add a screen to the cinema.
     *
     * @param screen screen to be added
     * @throws ScreenNumberAlreadyExistsException prevents a screen from being added with the same screen number
     */
    public void addScreen(Screen screen) throws ScreenNumberAlreadyExistsException {
        filmEdit.addScreen(screen);
        setupScreensFromDB();
    }

    /**
     * Gets screen object by screen number.
     *
     * @param screenNumber screen number of screen
     * @return screen matching the screen number
     */
    public Screen getScreen(Integer screenNumber) {
        return screens.get(screenNumber);
    }

    /**
     * Gets all screens currently existing on the cinema.
     *
     * @return all screens
     */
    public ArrayList<Screen> getScreens() {
        ArrayList<Screen> allScreens = new ArrayList<>();
        allScreens.addAll(screens.values());
        return allScreens;
    }

    /**
     * Removes a screen from the cinema.
     *
     * @param screen screen to be removed
     */
    public void deleteScreen(Screen screen) {
        filmEdit.deleteScreen(screen);
        screens.remove(screen.getScreenNumber());
    }
}
