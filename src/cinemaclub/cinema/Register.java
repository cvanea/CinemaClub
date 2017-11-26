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
            User user = new Staff(new UserCredentials(username, email, password));
            dataBase.writeToUserDetails(user.getName(), user);
        } else {
            validateUsername(username);

            User user = new Customer(new UserCredentials(username, email, password));
            dataBase.writeToUserDetails(user.getName(), user);
        }

        dataBase.printUserDatabase();
    }

    private void validateID(String staffId) throws IncorrectStaffIDException, StaffIDTakenException {
        // Checks whether the staffID is correct
        if (dataBase.getStaffIDValue(staffId) == null) {
            throw new IncorrectStaffIDException();
        } else if (!(staffId == null) && dataBase.getStaffIDValue(staffId)) {
            throw new StaffIDTakenException();
        }
    }

    private void validateUsername(String username) throws UsernameTakenException {

        if (dataBase.checkForUsername(username)) {
            throw new UsernameTakenException();
        }
    }

}
