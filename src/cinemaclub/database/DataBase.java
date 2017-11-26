package cinemaclub.database;

import cinemaclub.user.Customer;
import cinemaclub.user.Staff;
import cinemaclub.user.User;
import cinemaclub.user.UserCredentials;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
    private static DataBase ourInstance = new DataBase();

    private Map<String, String> staffID = new HashMap<>();
    private Map<String, User> userDetails = new HashMap<>();

    public static DataBase getInstance() {
        return ourInstance;
    }

    private DataBase() {
        readFromExternalDB();
    }

    public void addStaffID(String staffId, String username) {

        staffID.put(staffId, username);

        updateExternalStaffIDDB(staffID);
    }

    public void assignStaffID(String staffId, String username) {

        staffID.put(staffId, username);

        updateExternalStaffIDDB(staffID);
    }

    public String getStaffIDValue(String staffId) {

        return staffID.get(staffId);

    }

    public void writeToUserDetails(String userName, User user) {

        userDetails.put(userName, user);

        updateExternalUserDB(userDetails);
    }

    public Boolean checkForUsername(String username) {

        return userDetails.containsKey(username);
    }

    public User getUser(String userName) {

        return userDetails.get(userName);
    }

    public void printUserDatabase() {
        for (Map.Entry entry : userDetails.entrySet()) {
            System.out.print(entry.toString() + "\n");
        }
    }

    private void updateExternalStaffIDDB(Map<String, String> staffID) {

        try {
            FileWriter writer = new FileWriter("staffID.txt");

            for (Map.Entry entry : staffID.entrySet()) {
                writer.write(entry.toString() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void updateExternalUserDB(Map<String, User> userDetails) {

        try {
            FileWriter writer = new FileWriter("userDetails.txt");

            for (Map.Entry entry : userDetails.entrySet()) {
                writer.write(entry.toString() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void updateExternalFilmDB(Map<String, Film> filmDetails) {
//
//        try {
//            FileWriter writer = new FileWriter("filmDetails.txt", true);
//
//            for (Map.Entry entry : filmDetails.entrySet()) {
//                writer.write(entry.toString() + "\n");
//            }
//
//            writer.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private void updateExternalScreenDB(Map<String, Screen> screenDetails) {
//
//        try {
//            FileWriter writer = new FileWriter("screenDetails.txt", true);
//
//            for (Map.Entry entry : screenDetails.entrySet()) {
//                writer.write(entry.toString() + "\n");
//            }
//
//            writer.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    private void readFromExternalDB() {
        // TODO: Read from external databases

        readFromStaffDB();

        staffID.put("1", "Claudia");
        staffID.put("2", "Alex");
        staffID.put("3", "noStaff");
        staffID.put("4", "noStaff");
        staffID.put("5", "noStaff");
        staffID.put("6", "noStaff");
        staffID.put("7", "noStaff");
        staffID.put("8", "noStaff");
        staffID.put("9", "noStaff");
        staffID.put("10", "noStaff");

        userDetails.put("Claudia", new Staff(new UserCredentials("Claudia", "claudia.vanea@hotmail.co.uk","pass")));
        userDetails.put("Alex", new Staff(new UserCredentials("Alex", "Alex@hotmail.co.uk","passalex")));
        userDetails.put("Bob", new Customer(new UserCredentials("Bob", "bob@hotmail.co.uk", "pass2")));

    }

    private void readFromStaffDB() {

        String fileName = "staffID.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {






                System.out.println(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
