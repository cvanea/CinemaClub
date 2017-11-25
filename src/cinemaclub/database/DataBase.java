package cinemaclub.database;

import cinemaclub.user.*;

import java.util.HashMap;
import java.util.Map;

public class DataBase {
    private static DataBase ourInstance = new DataBase();
    private Map<String, Boolean> staffID = new HashMap<>();
    private Map<String, User> userDetails = new HashMap<>();

    public static DataBase getInstance() {
        return ourInstance;
    }

    private DataBase() {
        // TODO: Instantiation of DataBase comes from .txt database.

        readFromExternalDB();

        staffID.put("1", true);
        staffID.put("2", true);
        staffID.put("3", true);
        staffID.put("4", true);
        staffID.put("5", false);
        staffID.put("6", false);
        staffID.put("7", false);
        staffID.put("8", false);
        staffID.put("9", false);
        staffID.put("10", false);

        userDetails.put("Claudia", new Staff(new UserCredentials("Claudia", "claudia.vanea@hotmail.co.uk","pass")));
        userDetails.put("Bob", new Customer(new UserCredentials("Bob", "bob@hotmail.co.uk", "pass2")));
    }

    public void addStaffID(String staffId) {

        staffID.put(staffId, false);

        updateExternalDB();
    }

    public void useStaffID(String staffId) {

        staffID.put(staffId, true);

        updateExternalDB();
    }

    public Boolean getStaffIDValue(String staffId) {

        return staffID.get(staffId);

    }

    public void writeToUserDetails(String userName, User user) {

        userDetails.put(userName, user);

        updateExternalDB();
    }

    public Boolean checkForUsername(String username) {

        return userDetails.containsKey(username);
    }

    public User getUser(String userName) {

        return userDetails.get(userName);
    }

    public void printUserDatabase() {
        System.out.println(userDetails);
    }



    private void updateExternalDB() {
        // TODO: Write to external database

    }

    private void readFromExternalDB() {

        // TODO: Read from external database

    }
}
