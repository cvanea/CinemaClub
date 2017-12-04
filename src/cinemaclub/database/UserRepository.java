package cinemaclub.database;

import cinemaclub.user.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// TODO update to directly manipulate user objects without puts.
// TODO All CRUD stuff goes in here.
public class UserRepository implements Serializable {

    private DataBase dataBase;
    private Map<String, String> staffIDs = new HashMap<>();
    private Map<String, User> userDetails = new HashMap<>();

    UserRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void addStaffID(String staffId, String username) {

        staffIDs.put(staffId, username);

        dataBase.updateExternalDB();
    }

    public void assignStaffID(String staffId, String username) {

        staffIDs.put(staffId, username);

        dataBase.updateExternalDB();
    }

    public String getStaffIDValue(String staffId) {

        return staffIDs.get(staffId);
    }

    private Boolean isUsernameStaff(String username) {

        return staffIDs.containsValue(username);
    }

    public void writeToUserDetails(String username, User user) {

        userDetails.put(username, user);

        dataBase.updateExternalDB();
    }

    public Boolean checkForUsername(String username) {

        return userDetails.containsKey(username);
    }

    public User getUser(String userName) {

        return userDetails.get(userName);
    }

    public void setUsername(String oldUsername, String newUsername, User user) {
        userDetails.remove(oldUsername);
        userDetails.put(newUsername, user);

        dataBase.updateExternalDB();
    }

    public void setEmail(User user) {
        userDetails.put(user.getUsername(), user);

        dataBase.updateExternalDB();
    }

    public void setPassword(User user) {
        userDetails.put(user.getUsername(), user);

        dataBase.updateExternalDB();
    }

    public void deleteUser(String username) {
        userDetails.remove(username);

        dataBase.updateExternalDB();
    }

    public void deleteUserBooking(User user) {
        userDetails.put(user.getUsername(), user);

        dataBase.updateExternalDB();
    }

    public void addBooking(User user) {
        userDetails.put(user.getUsername(), user);

        dataBase.updateExternalDB();
    }

    public void printUserDatabase() {
        for (Map.Entry entry : userDetails.entrySet()) {
            System.out.print(entry.toString() + "\n");
        }
    }
}
