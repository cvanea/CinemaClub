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

        UserCredentials userCredentials = getDetails(username, email, password, userType);
        correctDetails(userCredentials);

        if (userCredentials.getUserType().equals("staff")) {
            // Instantiate customer or staff.

            return new Staff(userCredentials.getUserName(), userCredentials.getEmail(),
                userCredentials.getPassword());
        } else {

            return new Customer(userCredentials.getUserName(), userCredentials.getEmail(),
                userCredentials.getPassword());
        }
    }

    private UserCredentials getDetails(String username, String email, String password, String userType) {

            return new UserCredentials(username, email, password, userType);
    }

    private void correctDetails(UserCredentials userCredentials) throws UserDetailsDoNotExistException {
        UserCredentials savedCredentials = dataBase.getUserCredentials(userCredentials.getUserName());
        if (savedCredentials == null || !userCredentials.checkCredentials(savedCredentials)) {
            throw new UserDetailsDoNotExistException();
        }
    }

}
