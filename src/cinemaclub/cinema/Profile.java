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
        //TODO: MOVE USER CHANGES TO DATABASE TO AVOID DUPLICATION... FOOL
        user.setEmail(newEmail);
        userRepository.setEmail(user);
    }

    void setPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userRepository.setEmail(user);
    }

    ArrayList<Booking> getBookingHistory(Customer customer) throws NoBookingsException {
        validateExistingBookingHistory(customer);
        return customer.getBookings();
    }

    void deleteFutureBooking(Customer customer, String bookingTitle) {



    }


    private void validateUsername(String username) throws UsernameTakenException {

        if (userRepository.checkForUsername(username)) {
            throw new UsernameTakenException();
        }
    }

    private void validateExistingBookingHistory(Customer customer) throws NoBookingsException {

        // TODO create screen repository and refer to it here as well.
        if (screenRepository.noExistingBooking(customer)) {
            throw new NoBookingsException();
        }
    }

    void deleteUser(String username) {
        userRepository.deleteUser(username);
    }

}
