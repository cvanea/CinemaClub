package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.user.Booking;
import cinemaclub.user.Customer;
import cinemaclub.user.User;
import cinemaclub.user.UserCredentials;
import exceptions.NoBookingsException;
import exceptions.UsernameTakenException;

import java.util.ArrayList;

class Profile {

    private DataBase dataBase;

    Profile() {
        this.dataBase = DataBase.getInstance();
    }

    UserCredentials getProfileDetails(User user) {
        return user.getUserCredentials();
    }

    void setUsername(User user, String newUsername) throws UsernameTakenException {
        validateUsername(newUsername);
        String oldUsername = user.getUsername();
        user.setUserName(newUsername);
        dataBase.setUsername(oldUsername, newUsername, user);
    }

    void setEmail(User user, String newEmail) {
        user.setEmail(newEmail);
        dataBase.setEmail(user);
    }

    void setPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        dataBase.setEmail(user);
    }

    ArrayList<Booking> getBookingHistory(Customer customer) throws NoBookingsException {
        validateExistingBookingHistory(customer);
        return customer.getBookings();
    }

    void deleteFutureBooking(Customer customer, String bookingTitle) {
        


    }


    private void validateUsername(String username) throws UsernameTakenException {

        if (dataBase.checkForUsername(username)) {
            throw new UsernameTakenException();
        }
    }

    private void validateExistingBookingHistory(Customer customer) throws NoBookingsException {

        if (dataBase.noExistingBooking(customer)) {
            throw new NoBookingsException();
        }
    }

    void deleteUser(String username) {
        dataBase.deleteUser(username);
    }

}
