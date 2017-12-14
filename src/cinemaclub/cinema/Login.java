package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.UserRepository;
import cinemaclub.user.*;
import exceptions.UserDetailsDoNotExistException;
import exceptions.UserDetailsIncorrectException;

/**
 * Cinema system which validates and logs an existing user into the system.
 * If this succeeds, this sets the cinema current user to the user logging in.
 */
class Login {

    private UserRepository userRepository;

    /**
     * Gets the singleton database instance relevant to the login system.
     */
    Login() {
        this.userRepository = DataBase.getUserRepository();
    }

    /**
     * Attempts to log in a user by their username and password.
     * This works for both staff and customers.
     * Since usernames are uniquely stored in the database, this pairing will always find the right user.
     * First the username is checked in the database, then the username/password pair is checked in the database.
     * If successful, the matching User object is returned from the database.
     *
     * @param username username attempting to login with
     * @param password password attempting to login with
     * @return user which matched the username and password
     * @throws UserDetailsDoNotExistException incorrect username
     * @throws UserDetailsIncorrectException incorrect username/password pair
     */
    User loginUser(String username, String password)
        throws UserDetailsDoNotExistException, UserDetailsIncorrectException {

        validateUsername(username);
        validatePassword(username, password);

        return userRepository.getUser(username);
    }

    /**
     * Validates that the username entered exits in the database with a non-null pairing.
     *
     * @param username username to be validated
     * @throws UserDetailsDoNotExistException thrown exception
     */
    private void validateUsername(String username) throws UserDetailsDoNotExistException {
        if (userRepository.getUser(username) == null) {
            throw new UserDetailsDoNotExistException();
        }
    }

    /**
     * Validates that the username and password match in the database.
     * This allows a different error message to be thrown in case a user enters the correct username
     * but incorrect password.
     *
     * @param username username to be validated
     * @param password password to be validated
     * @throws UserDetailsIncorrectException thrown exception
     */
    private void validatePassword(String username, String password) throws UserDetailsIncorrectException {
        if (!userRepository.getUser(username).getPassword().equals(password)) {
            throw new UserDetailsIncorrectException();
        }
    }
}
