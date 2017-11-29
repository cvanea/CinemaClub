package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.user.User;
import cinemaclub.user.UserCredentials;
import exceptions.UsernameTakenException;

class Profile {

    private DataBase dataBase;

    Profile() {
        this.dataBase = DataBase.getInstance();
    }

    UserCredentials getProfileDetails(User user) {
        return user.getUserCredentials();
    }

    void setUsername(User user, String newUsername) throws UsernameTakenException {
        validateUsername(newUsername);
        String oldUsername = user.getName();
        user.setUserName(newUsername);
        dataBase.setUsername(oldUsername, newUsername, user);
    }

    void setEmail(User user, String newEmail) {
        user.setEmail(newEmail);
        dataBase.setEmail(user);
    }

    void setPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        dataBase.setEmail(user);
    }

    private void validateUsername(String username) throws UsernameTakenException {

        if (dataBase.checkForUsername(username)) {
            throw new UsernameTakenException();
        }
    }

    void deleteUser(String username) {
        dataBase.deleteUser(username);
    }

}
