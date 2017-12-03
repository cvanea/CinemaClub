package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.ScreenRepository;
import cinemaclub.database.UserRepository;
import cinemaclub.user.Booking;
import cinemaclub.user.Customer;
import cinemaclub.user.User;
import cinemaclub.user.UserCredentials;
import exceptions.NoBookingsException;
import exceptions.UsernameTakenException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Profile {

    private UserRepository userRepository;
    private ScreenRepository screenRepository;

    Profile() {
        this.userRepository = DataBase.getUserRepository();
        this.screenRepository = DataBase.getScreenRepository();
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
        userRepository.setEmail(user);
    }

    void setPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userRepository.setPassword(user);
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


    ArrayList<Booking> getBookingsHistory(User user) throws NoBookingsException {
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

//    void deleteFutureBooking(Customer customer, String bookingTitle) {
//
//
//
//    }


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

    void deleteUser(String username) {
        userRepository.deleteUser(username);
    }

}
