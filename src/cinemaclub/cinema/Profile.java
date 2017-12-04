package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.UserRepository;
import cinemaclub.user.Booking;
import cinemaclub.user.Customer;
import cinemaclub.user.User;
import cinemaclub.user.UserCredentials;
import exceptions.NoBookingsException;
import exceptions.NoFutureBookingsException;
import exceptions.NotAFutureBookingException;
import exceptions.UsernameTakenException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Profile {

    private UserRepository userRepository;

    Profile() {
        this.userRepository = DataBase.getUserRepository();
    }

    UserCredentials getProfileDetails(User user) {
        return user.getUserCredentials();
    }

    void setUsername(User user, String newUsername) throws UsernameTakenException {
        validateUsername(newUsername);
        String oldUsername = user.getUsername();
        user.setUserName(newUsername);
        userRepository.setUsername(oldUsername, newUsername, user);
    }

    void setEmail(User user, String newEmail) {
        user.setEmail(newEmail);
        userRepository.updateDB();
    }

    void setPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userRepository.updateDB();
    }

    ArrayList<Booking> getPastBookingsHistory(User user) throws NoBookingsException {
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
        return pastBookings;
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
        throws NotAFutureBookingException, NoBookingsException, NoFutureBookingsException {
        validateFutureBookingAsFuture(user, booking);
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            customer.deleteBooking(booking);
            userRepository.updateDB();
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
