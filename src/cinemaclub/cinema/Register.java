package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.UserRepository;
import cinemaclub.user.Customer;
import cinemaclub.user.Staff;
import cinemaclub.user.User;
import cinemaclub.user.UserCredentials;
import exceptions.*;

import java.util.ArrayList;

/**
 * Cinema system which validates and registers a user onto the cinema.
 */
class Register {

    private UserRepository userRepository;

    /**
     * Gets the singleton database instance relevant to the register system.
     */
    Register() {
        this.userRepository = DataBase.getUserRepository();
    }

    /**
     * Attempts to register a user by taking all relevant user detail information.
     * This works for both staff and customers.
     * Since a username must be unique, first the username is checked for an existing match in the database.
     * Then the other inputs are checked to not be empty strings.
     * Next the user attempting to register is checked to be a staff or customer.
     * If a staff, the staffID is validated and a new User object is created as a staff.
     * The staff ID is set both on the Staff object and in the database and the staff is finally added to the database.
     * Likewise, if a customer, a new User object is created as a customer with no bookings and is added to the database.
     *
     * @param username inputted username
     * @param email inputted email
     * @param password inputted password
     * @param firstName inputted first name
     * @param surname inputted surname
     * @param userType whether registering user if staff or customer
     * @param staffID the staff ID of the user. Null if a customer
     * @throws UsernameTakenException prevents user from registering with an existing username
     * @throws IncorrectStaffIDException alters user that their inputted staff ID is incorrect
     * @throws StaffIDTakenException prevents user from registering with an assigned staff ID
     * @throws EmptyUserInputException prevents user from registering with empty strings
     * @throws NotValidEmailException makes sure the email takes the correct email format
     */
    void registerUser(String username, String email, String password, String firstName, String surname, String userType, String staffID)
        throws UsernameTakenException, IncorrectStaffIDException, StaffIDTakenException, EmptyUserInputException, NotValidEmailException {
            validateUsername(username);
            validateEmail(email);
            validateInput(username);
            validateInput(email);
            validateInput(password);
            validateInput(firstName);
            validateInput(surname);

        if (userType.equals("staff")) {
            validateID(staffID);
            validateInput(staffID);

            User user = new Staff(new UserCredentials(username, email, password, firstName, surname));
            Staff staff = (Staff) user;
            staff.setStaffId(staffID);

            userRepository.assignStaffID(staffID, username);
            userRepository.addUser(user.getUsername(), user);
        } else {
            User user = new Customer(new UserCredentials(username, email, password, firstName, surname), new ArrayList<>());
            userRepository.addUser(user.getUsername(), user);
        }
    }

    /**
     * Validates that a staff ID exists and is not assigned to another staff.
     *
     * @param staffId staff ID to be validated
     * @throws IncorrectStaffIDException exception thrown
     * @throws StaffIDTakenException exception thrown
     */
    private void validateID(String staffId) throws IncorrectStaffIDException, StaffIDTakenException {
        if (userRepository.getStaffIDValue(staffId) == null) {
            throw new IncorrectStaffIDException();
        } else if (!(staffId == null) && !userRepository.getStaffIDValue(staffId).equals("noStaff")) {
            throw new StaffIDTakenException();
        }
    }

    /**
     * Validates that a username does not already exist in the database.
     *
     * @param username username to be validate
     * @throws UsernameTakenException exception thrown
     */
    private void validateUsername(String username) throws UsernameTakenException {
        if (userRepository.checkForUsername(username)) {
            throw new UsernameTakenException();
        }
    }

    /**
     * Validates that an input isn't an empty string.
     *
     * @param input input to be validated
     * @throws EmptyUserInputException exception thrown
     */
    private void validateInput(String input) throws EmptyUserInputException {
        if (input.equals("")) {
            throw new EmptyUserInputException();
        }
    }

    /**
     * Validates that an email has an "@" and "." afterwards.
     *
     * @param email email to be validated
     * @throws NotValidEmailException exception thrown
     */
    private void validateEmail(String email) throws NotValidEmailException {
        if (!email.contains("@")) {
            throw new NotValidEmailException();
        }

        String[] splitEmail = email.split("@", 2);
        String address = splitEmail[1];

        if (!address.contains(".")) {
            throw new NotValidEmailException();
        }
    }
}
