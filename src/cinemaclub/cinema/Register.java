package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.UserRepository;
import cinemaclub.user.Customer;
import cinemaclub.user.Staff;
import cinemaclub.user.User;
import cinemaclub.user.UserCredentials;
import exceptions.*;

import java.util.ArrayList;

class Register {

    private UserRepository userRepository;

    Register() {
        this.userRepository = DataBase.getUserRepository();
    }

    void registerUser(String username, String email, String password, String firstName, String surname, String userType, String staffID)
        throws UsernameTakenException, IncorrectStaffIDException, StaffIDTakenException, EmptyUserInputException, NotValidEmailException {
        //TODO add email validation
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

    private void validateID(String staffId) throws IncorrectStaffIDException, StaffIDTakenException {
        // Checks whether the staffID is correct
        if (userRepository.getStaffIDValue(staffId) == null) {
            throw new IncorrectStaffIDException();
        } else if (!(staffId == null) && !userRepository.getStaffIDValue(staffId).equals("noStaff")) {
            throw new StaffIDTakenException();
        }
    }

    private void validateUsername(String username) throws UsernameTakenException {
        if (userRepository.checkForUsername(username)) {
            throw new UsernameTakenException();
        }
    }

    private void validateInput(String input) throws EmptyUserInputException {
        if (input.equals("")) {
            throw new EmptyUserInputException();
        }
    }

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
