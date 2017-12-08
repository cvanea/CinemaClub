package cinemaclub.cinema;

import cinemaclub.user.Booking;
import cinemaclub.user.Customer;
import cinemaclub.user.User;
import cinemaclub.user.UserCredentials;
import exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//TODO Add functionality for staff to export a list of films with dates, times, number of booked and available seats.
//TODO Make proper script which creates a new cinema with default filled database entries.
//TODO Checking when adding showings for overlapping films.
//TODO INIT WITH ONE DEFAULT STAFF AND ONE DEFAULT CUSTOMER
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
//        screens.put(1, filmDisplay.getScreenByNumber(1));

        screens = setupScreens();
        addInitialFilms();
        addInitialShowings();
        addInitialStaffID();
    }

    //Sets up the number of screens in the cinema and their seat number
    private Map<Integer, Screen> setupScreens() {
        try {
            validateExistingScreen(1);
            Map<Integer, Screen> screensMap = new HashMap<>();
            screensMap.put(1, new Screen(1, 5, 10));
            return screensMap;
        } catch (ScreenAlreadySetupException e) {
            Map<Integer, Screen> screenMap = new HashMap<>();
            screenMap.put(1, filmDisplay.getScreenByNumber(1));
            return screenMap;
        }
    }

    private void validateExistingScreen(Integer screenNumber) throws ScreenAlreadySetupException {
        if (filmDisplay.getScreenByNumber(screenNumber) != null) {
            throw new ScreenAlreadySetupException();
        }
    }

    private void addInitialFilms() {
        try {
            this.addFilm("UP", "/UP.jpg", "A great film", "01:00");
            this.addFilm("Walle", "/walle.jpg", "A another great film", "02:00");
        } catch (FilmExistsException e) {
            System.out.println("Initial movies registered");
        }
    }

    private void addInitialShowings() {
        this.addFilmToShowings("2017-12-15", "13:00", this.getFilmByTitle("UP"));
        this.addFilmToShowings("2017-12-15", "12:00", this.getFilmByTitle("Walle"));
    }

    private void addInitialStaffID() {
        if (profile.getAllStaffByID().isEmpty()) {
            profile.addStaffID("1", "noStaff");
            profile.addStaffID("2", "noStaff");
            profile.addStaffID("3", "noStaff");
            profile.addStaffID("4", "noStaff");
            profile.addStaffID("5", "noStaff");
        }
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

    public void loginUser(String username, String password)
        throws UserDetailsDoNotExistException, UserDetailsIncorrectException {
        currentUser = login.loginUser(username, password);
    }

    public void registerUser(String username, String email, String password, String firstName, String surname, String userType, String staffID)
        throws UsernameTakenException, IncorrectStaffIDException, StaffIDTakenException {
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

    public void bookFilm(String date, String time, Screen screen, String seatRow, int seatNumber)
        throws SeatAlreadyTakenException, SeatNotFoundException, ShowingDoesNotExistException {
        bookingSystem.bookFilm(currentUser, date, time, screen, seatRow, seatNumber);
    }

    public ArrayList<Film> getFilmsByDate(String date) throws PastDateException {
        return filmDisplay.getFilmsByDateScreen(date, this.getScreen(1));
    }

    public ArrayList<Showing> getShowingsByDate(String date) throws PastDateException {
        return filmDisplay.getShowingsByDate(date, this.getScreen(1));
    }

    public ArrayList<String> getTimesByFilm(Film film) {
        return filmDisplay.getTimesByFilm(this.getScreen(1), film);
    }

    public void addFilmToShowings(String date, String time, Film film) {
        //TODO ADD EXCEPTION IN CASE FILM IS ALREADY SHOWING AT THAT TIME
        filmEdit.addFilmToShowings(this.getScreen(1), date, time, film);
    }

    public void deleteShowing(String date, String time) {
        filmEdit.deleteShowing(this.getScreen(1), date, time);
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

    public void deleteFilm(String title) {
        filmEdit.deleteFilm(title);
    }

    public void deleteUser(String username) {
        profile.deleteUser(username);
    }
}
