package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.user.*;
import exceptions.UserDetailsDoNotExistException;
import exceptions.UserDetailsIncorrectException;

class Login {

    private DataBase dataBase;

    Login() {
        this.dataBase = DataBase.getInstance();
    }

    User loginUser(String username, String email, String password)
        throws UserDetailsDoNotExistException, UserDetailsIncorrectException {

        UserCredentials userCredentials = new UserCredentials(username, email, password);
        validateDetails(userCredentials);

        return dataBase.getUser(username);
    }

    private void validateDetails(UserCredentials userCredentials)
        throws UserDetailsDoNotExistException, UserDetailsIncorrectException {
        User user = dataBase.getUser(userCredentials.getUserName());
        UserCredentials savedCredentials;

        if (user == null) {
            throw new UserDetailsDoNotExistException();
        } else {
            savedCredentials = user.getUserCredentials();
        }

        if (!userCredentials.checkCredentials(savedCredentials)) {
            throw new UserDetailsIncorrectException();
        }
    }
}
