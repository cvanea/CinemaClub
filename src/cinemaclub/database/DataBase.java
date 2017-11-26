package cinemaclub.database;

import cinemaclub.user.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
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
        readFromExternalDB();
    }

    public void addStaffID(String staffId) {

        staffID.put(staffId, false);

//        updateExternalStaffIDDB();
    }

    public void useStaffID(String staffId) {

        staffID.put(staffId, true);

//        updateExternalStaffIDDB();
    }

    public Boolean getStaffIDValue(String staffId) {

        return staffID.get(staffId);

    }

    public void writeToUserDetails(String userName, User user) {

        userDetails.put(userName, user);

//        updateExternalUserDB();
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

    private void updateExternalStaffIDDB(HashMap<String, Boolean> staffID) throws IOException {
        // TODO: Write to external database

        try {
            FileWriter writer = new FileWriter("staffID.txt", true);

            writer.write(staffID.toString());

            writer.close();

        } catch (NoSuchFileException e) {
            FileWriter writer = new FileWriter("staffID.txt");

            writer.write(staffID.toString());

            writer.close();
        }

    }

    private void updateExternalUserDB(HashMap<String, User> userDetails) throws IOException {
        // TODO: Write to external database

        try {
            FileWriter writer = new FileWriter("userDetails.txt", true);

            writer.write(userDetails.toString());

            writer.close();

        } catch (NoSuchFileException e) {
            FileWriter writer = new FileWriter("userDetails.txt");

            writer.write(userDetails.toString());

            writer.close();
        }
    }

    private void updateExternalFilmDB() {
        // TODO: Write to external database

    }

    private void updateExternalScreenDB() {
        // TODO: Write to external database

    }

    private void readFromExternalDB() {
        // TODO: Read from external database

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
}
