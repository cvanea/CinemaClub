package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.UserRepository;
import cinemaclub.user.*;
import exceptions.IncorrectStaffIDException;
import exceptions.StaffIDTakenException;
import exceptions.UsernameTakenException;

import java.util.ArrayList;

class Register {

    private UserRepository userRepository;

    Register() {
        this.userRepository = DataBase.getUserRepository();
    }

    void registerUser(String username, String email, String password, String userType, String staffID)
        throws UsernameTakenException, IncorrectStaffIDException, StaffIDTakenException  {

        if (userType.equals("staff")) {
            validateID(staffID);
            validateUsername(username);

            User user = new Staff(new UserCredentials(username, email, password));
            userRepository.assignStaffID(staffID, username);
            userRepository.writeToUserDetails(user.getUsername(), user);
        } else {
            validateUsername(username);

            User user = new Customer(new UserCredentials(username, email, password), new ArrayList<>());
            userRepository.writeToUserDetails(user.getUsername(), user);
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

}
