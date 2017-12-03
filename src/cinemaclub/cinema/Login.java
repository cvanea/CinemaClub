package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.UserRepository;
import cinemaclub.user.*;
import exceptions.UserDetailsDoNotExistException;
import exceptions.UserDetailsIncorrectException;

class Login {

    private UserRepository userRepository;

    Login() {
        this.userRepository = DataBase.getUserRepository();
    }

    User loginUser(String username, String email, String password)
        throws UserDetailsDoNotExistException, UserDetailsIncorrectException {

        UserCredentials userCredentials = new UserCredentials(username, email, password);
        validateDetails(userCredentials);

        return userRepository.getUser(username);
    }

    private void validateDetails(UserCredentials userCredentials)
        throws UserDetailsDoNotExistException, UserDetailsIncorrectException {
        User user = userRepository.getUser(userCredentials.getUsername());
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
