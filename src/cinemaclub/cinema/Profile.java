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

class Profile {

    private UserRepository userRepository;
    private ScreenRepository screenRepository;

    Profile() {
        this.userRepository = DataBase.getUserRepository();
        this.screenRepository = DataBase.getScreenRepository();
    }

    User getUser(String username) {
        return userRepository.getUser(username);
    }

    UserCredentials getProfileDetails(User user) {
        return user.getUserCredentials();
    }

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

    ArrayList<String> getAllStaffByID() {
        return userRepository.getAllStaffByID();
    }

    void setEmail(User user, String newEmail) {
        user.setEmail(newEmail);
        userRepository.updateDB();
    }

    void setPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userRepository.updateDB();
    }

    void setFirstName(User user, String firstName) {
        user.setFirstName(firstName);
        userRepository.updateDB();
    }

    void setSurname(User user, String surname) {
        user.setSurname(surname);
        userRepository.updateDB();
    }

    ArrayList<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public Map<String, String> getStaffIDs() {
        return userRepository.getStaffIDs();
    }

    String getStaffIdByUsername(String username) {
        return userRepository.getStaffIdByUsername(username);
    }

    void addStaffID(String id, String username) {
        userRepository.addStaffID(id, username);
    }

    void deleteStaffId(String staffId) throws StaffIdNotEmptyException {
        validateStaffIdIsEmpty(staffId);
        userRepository.deleteStaffId(staffId);
    }

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

    private ArrayList<Booking> getBookingsHistory(User user) throws NoBookingsException {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            validateExistingBookingsHistory(customer);
            return customer.getBookings();
        } else return null;
    }

    ArrayList<Booking> getBookingsHistory(Customer customer) throws NoBookingsException {
            validateExistingBookingsHistory(customer);
            return customer.getBookings();
    }

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

    private void validateStaffIdIsEmpty(String staffId) throws StaffIdNotEmptyException {
        if (!userRepository.getStaffIDValue(staffId).equals("noStaff")) {
            throw new StaffIdNotEmptyException();
        }
    }

    private void validateUsername(String username) throws UsernameTakenException {
        if (userRepository.checkForUsername(username)) {
            throw new UsernameTakenException();
        }
    }

    private void validateExistingBookingsHistory(Customer customer) throws NoBookingsException {
        if (customer.noExistingBookings()) {
            throw new NoBookingsException();
        }
    }

    private void validateFutureBookingAsFuture(User user, Booking booking)
        throws NotAFutureBookingException, NoBookingsException, NoFutureBookingsException {
        if (!(this.getFutureBookingsHistory(user).contains(booking))){
            throw new NotAFutureBookingException();
        }
    }

    void deleteUser(String username) {
        userRepository.deleteUser(username);
    }

}
