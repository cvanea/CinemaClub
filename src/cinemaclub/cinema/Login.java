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

    User loginUser(String username, String password)
        throws UserDetailsDoNotExistException, UserDetailsIncorrectException {

        validateUsername(username);
        validatePassword(username, password);

        return userRepository.getUser(username);
    }

    private void validateUsername(String username)
        throws UserDetailsDoNotExistException {

        if (userRepository.getUser(username) == null) {
            throw new UserDetailsDoNotExistException();
        }
    }

    private void validatePassword(String username, String password) throws UserDetailsIncorrectException {
        if (!userRepository.getUser(username).getPassword().equals(password)) {
            throw new UserDetailsIncorrectException();
        }
    }

}
