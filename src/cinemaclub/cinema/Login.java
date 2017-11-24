package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.UserCredentials;
import cinemaclub.user.*;
import exceptions.UserDetailsDoNotExistException;

class Login {

    private DataBase dataBase;

    Login() {
        this.dataBase = DataBase.getInstance();
    }

    User loginUser(String username, String email, String password, String userType)
        throws UserDetailsDoNotExistException {

        UserCredentials userCredentials = new UserCredentials(username, email, password, userType);
        validateDetails(userCredentials);

        if (userCredentials.getUserType().equals("staff")) {
            // Instantiate customer or staff.

            return new Staff(userCredentials);
        } else {

            return new Customer(userCredentials);
        }
    }

    private void validateDetails(UserCredentials userCredentials) throws UserDetailsDoNotExistException {
        UserCredentials savedCredentials = dataBase.getUserCredentials(userCredentials.getUserName());
        if (savedCredentials == null || !userCredentials.checkCredentials(savedCredentials)) {
            throw new UserDetailsDoNotExistException();
        }
    }
}
