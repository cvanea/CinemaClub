package cinemaclub.database;

import cinemaclub.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserRepository implements Serializable {

    private DataBase dataBase;
    private Map<String, String> staffIDs = new HashMap<>();
    private Map<String, User> userDetails = new HashMap<>();

    public UserRepository(DataBase dataBase) {
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

    public ArrayList<String> getAllStaffByID() {
        return new ArrayList<>(staffIDs.values());
    }

    private Boolean isStaff(String username) {
        return staffIDs.containsValue(username);
    }

    public void addUser(String username, User user) {
        userDetails.put(username, user);

        dataBase.updateExternalDB();
    }

    public Boolean checkForUsername(String username) {
        return userDetails.containsKey(username);
    }

    public User getUser(String userName) {
        return userDetails.get(userName);
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        allUsers.addAll(userDetails.values());

        return allUsers;
    }

    public void setUsername(String oldUsername, String newUsername, User user) {
        userDetails.remove(oldUsername);
        userDetails.put(newUsername, user);

        dataBase.updateExternalDB();
    }

    public void updateDB() {
        dataBase.updateExternalDB();
    }

    public void deleteUser(String username) {
        userDetails.remove(username);

        dataBase.updateExternalDB();
    }

    public void printUserDatabase() {
        for (Map.Entry entry : userDetails.entrySet()) {
            System.out.print(entry.toString() + "\n");
        }
    }
}
