package cinemaclub.database;

import cinemaclub.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class containing all database methods related to users.
 * Whenever data is changed in this class, the external txt file database is updated.
 * Serializable allows it to be saved as bytes to an external database file.
 */
public class UserRepository implements Serializable {

    private DataBase dataBase;
    private Map<String, String> staffIDs = new HashMap<>();
    private Map<String, User> userDetails = new HashMap<>();

    /**
     * Instantiates the userRepository with the one database instance to guarantee data safety.
     *
     * @param dataBase dataBase object
     */
    public UserRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    /**
     * Gets the staff ID/username map.
     *
     * @return map
     */
    public Map<String, String> getStaffIDs() {
        return staffIDs;
    }

    /**
     * Gets a staff ID by its matching username or returns null.
     *
     * @param username username to match against
     * @return matched staff ID
     */
    public String getStaffIdByUsername(String username) {

        for (Map.Entry<String, String> entry : staffIDs.entrySet()) {
            if (entry.getValue().equals(username)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Adds a new staff ID to the database.
     *
     * @param staffId new staff ID
     * @param username either a username or "noStaff" to show unassigned.
     */
    public void addStaffID(String staffId, String username) {
        staffIDs.put(staffId, username);

        dataBase.updateExternalDB();
    }

    /**
     * Assigns a staff's username to a staff ID.
     *
     * @param staffId staff ID to be assigned
     * @param username username to be assigned
     */
    public void assignStaffID(String staffId, String username) {
        staffIDs.put(staffId, username);

        dataBase.updateExternalDB();
    }

    /**
     * Removes a staff ID key/pair from the hashmap.
     *
     * @param staffId staff ID key to remove
     */
    public void deleteStaffId(String staffId) {
        staffIDs.remove(staffId);

        dataBase.updateExternalDB();
    }

    /**
     * Gets the username paired with a staff ID key.
     *
     * @param staffId staff ID key
     * @return username
     */
    public String getStaffIDValue(String staffId) {
        return staffIDs.get(staffId);
    }

    /**
     * Adds a user to the user hashmap.
     *
     * @param username username of user
     * @param user user to be added
     */
    public void addUser(String username, User user) {
        userDetails.put(username, user);

        dataBase.updateExternalDB();
    }

    /**
     * Checks that the username of a user exists, and therefore whether a user exsits.
     *
     * @param username username to check
     * @return true if username exists and false otherwise
     */
    public Boolean checkForUsername(String username) {
        return userDetails.containsKey(username);
    }

    /**
     * Gets a User object by the username key.
     *
     * @param userName username key
     * @return paired User object
     */
    public User getUser(String userName) {
        return userDetails.get(userName);
    }

    /**
     * Gets all users in the database.
     *
     * @return all users
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        allUsers.addAll(userDetails.values());

        return allUsers;
    }

    /**
     * Updates a username for a user.
     * As the username is a key, it first removes the old pair and then adds the new one.
     *
     * @param oldUsername old username key
     * @param newUsername new username key
     * @param user same user
     */
    public void setUsername(String oldUsername, String newUsername, User user) {
        userDetails.remove(oldUsername);
        userDetails.put(newUsername, user);

        dataBase.updateExternalDB();
    }

    /**
     * Deletes a user from the database by their username key.
     *
     * @param username username key
     */
    public void deleteUser(String username) {
        userDetails.remove(username);

        dataBase.updateExternalDB();
    }

    /**
     * Updates the external txt file to save any simple changes to the offline database.
     */
    public void updateDB() {
        dataBase.updateExternalDB();
    }

}
