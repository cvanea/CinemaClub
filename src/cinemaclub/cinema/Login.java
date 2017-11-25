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
        UserCredentials savedCredentials = dataBase.getUser(userCredentials.getUserName()).getUserCredentials();
        if (savedCredentials == null) {
            throw new UserDetailsDoNotExistException();
        } else if (!userCredentials.checkCredentials(savedCredentials)) {
            throw new UserDetailsIncorrectException();
        }
    }
}
