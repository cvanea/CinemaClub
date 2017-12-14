package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.ScreenRepository;
import cinemaclub.database.UserRepository;
import cinemaclub.user.*;
import exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

/**
 * Cinema system which validates and allows update and querying of user information.
 */
class Profile {

    private UserRepository userRepository;
    private ScreenRepository screenRepository;

    /**
     * Gets the singleton database instance relevant to the profile system.
     */
    Profile() {
        this.userRepository = DataBase.getUserRepository();
        this.screenRepository = DataBase.getScreenRepository();
    }

    /*
    Both staff and customer
     */

    /**
     * Gets UserCredentials object for a particular user.
     *
     * @param user user to retrieve object
     * @return UserCredentials object
     */
    UserCredentials getProfileDetails(User user) {
        return user.getUserCredentials();
    }

    /**
     * Changes a user's username.
     * First it validates that the new username does not currently exist in the database.
     * Then it sets the new username on the user and then updates the database.
     * If the user is a staff, it also updates the staff ID database with the new username.
     *
     * @param user user changing their username
     * @param newUsername new username
     * @throws UsernameTakenException prevents changing username to one that already exists
     */
    void setUsername(User user, String newUsername) throws UsernameTakenException {
        validateUsername(newUsername);
        String oldUsername = user.getUsername();
        user.setUsername(newUsername);
        userRepository.setUsername(oldUsername, newUsername, user);

        if (user instanceof Staff) {
            String staffId = userRepository.getStaffIdByUsername(oldUsername);
            userRepository.assignStaffID(staffId, newUsername);
        }
    }

    /**
     * Changes a user's email on object and in database.
     *
     * @param user user whose email is changing
     * @param newEmail new email
     */
    void setEmail(User user, String newEmail) {
        user.setEmail(newEmail);
        userRepository.updateDB();
    }

    /**
     * Changes a user's password on object and in database.
     *
     * @param user user whose password is changing
     * @param newPassword new password
     */
    void setPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userRepository.updateDB();
    }

    /**
     * Changes a user's first name on object and in database.
     *
     * @param user user whose first name is changing
     * @param firstName new first name
     */
    void setFirstName(User user, String firstName) {
        user.setFirstName(firstName);
        userRepository.updateDB();
    }

    /**
     * Changes a user's surname on object and in database.
     *
     * @param user user whose surname is changing
     * @param surname new surname
     */
    void setSurname(User user, String surname) {
        user.setSurname(surname);
        userRepository.updateDB();
    }

    /**
     * Gets a user by their username.
     *
     * @param username username
     * @return user matching username
     */
    User getUser(String username) {
        return userRepository.getUser(username);
    }

    /**
     * Gets all users registered in the cinema.
     *
     * @return all users
     */
    ArrayList<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    /**
     * Removes a user from the database.
     *
     * @param username username of user to be removed
     */
    void deleteUser(String username) {
        userRepository.deleteUser(username);
    }

    /*
    Customer
     */

    /**
     * Gets all bookings which are past by checking against current time when called.
     * First the whole bookings history for a user is retrieved.
     * Then the date and time of each booking is checked against current time.
     * If the booking is in the past, the booking is added to the returned array list.
     * If there are no past bookings, an exception is thrown.
     *
     * @param user user who holds the booking
     * @return all past bookings
     * @throws NoBookingsException checks that there are any bookings
     * @throws NoPastBookingsException exception thrown for no past bookings
     */
    ArrayList<Booking> getPastBookingsHistory(User user) throws NoBookingsException, NoPastBookingsException {
        ArrayList<Booking> allBookings = getBookingsHistory(user);
        ArrayList<Booking> pastBookings = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String timeNow = LocalDateTime.now().format(formatter);

        for (Booking booking : allBookings) {
            String bookingDateTime = booking.getDate() + " " + booking.getTime();
            if (bookingDateTime.compareTo(timeNow) < 0) {
                pastBookings.add(booking);
            }
        }

        if (pastBookings.isEmpty()) {
            throw new NoPastBookingsException();
        } else return pastBookings;
    }

    /**
     * Gets all bookings which are future by checking against current time when called.
     * First the whole bookings history for a user is retrieved.
     * Then the date and time of each booking is checked against current time.
     * If the booking is in the future, the booking is added to the returned array list.
     * If there are no future bookings, an exception is thrown.
     *
     * @param user user who holds the booking
     * @return all future bookings
     * @throws NoBookingsException checks that there are any bookings
     * @throws NoFutureBookingsException exception thrown for no future bookings
     */
    ArrayList<Booking> getFutureBookingsHistory(User user) throws NoBookingsException, NoFutureBookingsException {
        ArrayList<Booking> allBookings = getBookingsHistory(user);
        ArrayList<Booking> futureBookings = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String timeNow = LocalDateTime.now().format(formatter);

        for (Booking booking : allBookings) {
            String bookingDateTime = booking.getDate() + " " + booking.getTime();
            if (bookingDateTime.compareTo(timeNow) > 0) {
                futureBookings.add(booking);
            }
        }

        if (futureBookings.isEmpty()) {
            throw new NoFutureBookingsException();
        } else return futureBookings;
    }

    /**
     * Gets booking history for a particular customer.
     * First checks that they have any bookings..
     *
     * @param customer customer who holds the booking
     * @return all bookings
     * @throws NoBookingsException checks that there are any bookings
     */
    ArrayList<Booking> getBookingsHistory(Customer customer) throws NoBookingsException {
        validateExistingBookingsHistory(customer);
        return customer.getBookings();
    }

    /**
     * Gets booking history for a particular user.
     * This is called if the passed user is of user type rather than customer.
     * First the user is cast to a Customer.
     * Then they are checked for no bookings.
     *
     * @param user user who holds the booking
     * @return all bookings or null if there are no bookings
     * @throws NoBookingsException checks that there are any bookings
     */
    private ArrayList<Booking> getBookingsHistory(User user) throws NoBookingsException {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            validateExistingBookingsHistory(customer);
            return customer.getBookings();
        } else return null;
    }

    /**
     * Attempts to remove a particular booking for a particular user.
     * First the booking is checked to be a future booking, as only future bookings can be deleted.
     * Then the user is cast to a Customer.
     * Then their booking is removed on the Customer object.
     * The showing is retrieved to remove the taken seat from the Showing object.
     * Finally the database is updated.
     *
     * @param user user who holds the booking
     * @param booking booking to be removed
     * @throws NotAFutureBookingException checks that the booking is a future booking
     * @throws NoBookingsException checks that there are any bookings
     * @throws NoFutureBookingsException check that the user has future bookings
     * @throws SeatNotFoundException checks that the seat exists on the screen
     */
    void deleteFutureBooking(User user, Booking booking)
        throws NotAFutureBookingException, NoBookingsException, NoFutureBookingsException, SeatNotFoundException {
        validateFutureBookingAsFuture(user, booking);
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            customer.deleteBooking(booking);
            Showing showing = booking.getShowing();

            String seat = booking.getSeat();
            String[] splitSeat = seat.split("(?!^)", 2);

            showing.unbookSeat(splitSeat[0], Integer.parseInt(splitSeat[1]));
            userRepository.updateDB();
            screenRepository.updateDB();
        }
    }

    /*
    Staff
     */

    /**
     * Adds a new staff ID to the database.
     *
     * @param id new Staff ID
     * @param username eithert a username or "noStaff" to show unassigned.
     */
    void addStaffID(String id, String username) {
        userRepository.addStaffID(id, username);
    }

    /**
     * Gets map of staff IDs to usernames.
     *
     * @return map of staff ID to username
     */
    Map<String, String> getStaffIDs() {
        return userRepository.getStaffIDs();
    }

    /**
     * Gets a staff ID for a username.
     *
     * @param username staff username with staff ID
     * @return staff ID
     */
    String getStaffIdByUsername(String username) {
        return userRepository.getStaffIdByUsername(username);
    }



    /**
     * Attempts to remove an unassigned staff ID.
     *
     * @param staffId staff ID to be removed
     * @throws StaffIdNotEmptyException prevents removal of an assigned staff ID
     */
    void deleteStaffId(String staffId) throws StaffIdNotEmptyException {
        validateStaffIdIsEmpty(staffId);
        userRepository.deleteStaffId(staffId);
    }

    /*
    Validation
     */

    /**
     * Validates that a staff ID is not assigned.
     *
     * @param staffId staff ID to be validated
     * @throws StaffIdNotEmptyException thrown exception
     */
    private void validateStaffIdIsEmpty(String staffId) throws StaffIdNotEmptyException {
        if (!userRepository.getStaffIDValue(staffId).equals("noStaff")) {
            throw new StaffIdNotEmptyException();
        }
    }

    /**
     * Validates that a username isn't already in the database.
     *
     * @param username username to be validated
     * @throws UsernameTakenException thrown exception
     */
    private void validateUsername(String username) throws UsernameTakenException {
        if (userRepository.checkForUsername(username)) {
            throw new UsernameTakenException();
        }
    }

    /**
     * Validates that a customer has a least one booking.
     *
     * @param customer customer to be validated
     * @throws NoBookingsException thrown exception
     */
    private void validateExistingBookingsHistory(Customer customer) throws NoBookingsException {
        if (customer.noExistingBookings()) {
            throw new NoBookingsException();
        }
    }

    /**
     * Validates that a booking for a user is a future booking.
     *
     * @param user user who holds the booking
     * @param booking booking to be validated
     * @throws NotAFutureBookingException thrown exception
     * @throws NoBookingsException thrown exception
     * @throws NoFutureBookingsException thrown exception
     */
    private void validateFutureBookingAsFuture(User user, Booking booking)
        throws NotAFutureBookingException, NoBookingsException, NoFutureBookingsException {
        if (!(this.getFutureBookingsHistory(user).contains(booking))){
            throw new NotAFutureBookingException();
        }
    }
}
