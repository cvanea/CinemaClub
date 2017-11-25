package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.user.*;
import exceptions.IncorrectStaffIDException;
import exceptions.StaffIDTakenException;
import exceptions.UsernameTakenException;

class Register {

    private DataBase dataBase;

    Register() {
        this.dataBase = DataBase.getInstance();
    }

    void registerUser(String username, String email, String password, String userType, String staffID)
        throws UsernameTakenException, IncorrectStaffIDException, StaffIDTakenException  {

        if (userType.equals("staff")) {
            validateID(staffID);
            validateUsername(username);

            dataBase.useStaffID(staffID);
            User userCredentials = new Staff(new UserCredentials(username, email, password, userType));
            dataBase.writeToUserDetails(userCredentials.getName(), userCredentials);
        } else {
            validateUsername(username);

            User userCredentials = new Customer(new UserCredentials(username, email, password, userType));
            dataBase.writeToUserDetails(userCredentials.getName(), userCredentials);
        }

        dataBase.printUserDatabase();
    }

    private void validateID(String staffId) throws IncorrectStaffIDException, StaffIDTakenException {
        // Checks whether the staffID is correct
        if (!(staffId == null) && dataBase.getStaffIDValue(staffId)) {
            throw new StaffIDTakenException();
        } else if (dataBase.getStaffIDValue(staffId) == null) {
            throw new IncorrectStaffIDException();
        }
    }

    private void validateUsername(String username) throws UsernameTakenException {

        if (dataBase.checkForUsername(username)) {
            throw new UsernameTakenException();
        }
    }

}
