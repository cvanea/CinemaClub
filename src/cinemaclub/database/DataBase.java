package cinemaclub.database;

import cinemaclub.cinema.Film;
import cinemaclub.user.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class DataBase {
    private static DataBase ourInstance = new DataBase();

    private Map<String, String> staffIDs = new HashMap<>();
    private Map<String, User> userDetails = new HashMap<>();
    private Map<String, Film> films = new HashMap<>();

    public static DataBase getInstance() {
        return ourInstance;
    }

    private DataBase() {
        
        readFromExternalDB();
    }

    public void addStaffID(String staffId, String username) {

        staffIDs.put(staffId, username);

        updateExternalStaffIDDB(staffIDs);
    }

    public void assignStaffID(String staffId, String username) {

        staffIDs.put(staffId, username);

        updateExternalStaffIDDB(staffIDs);
    }

    public String getStaffIDValue(String staffId) {

        return staffIDs.get(staffId);

    }

    private Boolean isUsernameStaff(String username) {

        return staffIDs.containsValue(username);
    }

    public void writeToUserDetails(String username, User user) {

        userDetails.put(username, user);

        updateExternalUserDB(userDetails);
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

        updateExternalUserDB(userDetails);
    }

    public void setEmail(User user) {
        userDetails.put(user.getUsername(), user);

        updateExternalUserDB(userDetails);
    }

    public void setPassword(User user) {
        userDetails.put(user.getUsername(), user);

        updateExternalUserDB(userDetails);
    }

    public void deleteUser(String username) {
        userDetails.remove(username);

        updateExternalUserDB(userDetails);
    }

    public Boolean noExistingBooking(Customer customer) {
        return customer.getBookings().isEmpty();
    }

    public void printUserDatabase() {
        for (Map.Entry entry : userDetails.entrySet()) {
            System.out.print(entry.toString() + "\n");
        }
    }

    private void updateExternalStaffIDDB(Map<String, String> staffID) {

        try {
            FileWriter writer = new FileWriter("staffIDs.txt");

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

    private void updateExternalFilmDB(Map<String, Film> films) {

        try {
            FileWriter writer = new FileWriter("films.txt");

            for (Map.Entry entry : films.entrySet()) {
                writer.write(entry.toString() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
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

        readFromStaffDB();
        readFromUserDB();
        readFromFilmDB();
    }

    private void readFromStaffDB() {

        String fileName = "staffIDs.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {
                // Reads one line at a time

                String[] tokens = line.split("=");

                String key = tokens[0];
                String value = tokens[1];

                staffIDs.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromUserDB() {

        String fileName = "userDetails.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {
                // Reads one line at a time

                String[] tokens = line.split("=");

                String key = tokens[0];

                String[] valueTokens = tokens[1].split(", ", 4);

                UserCredentials userCredentials = new UserCredentials(valueTokens[0], valueTokens[1], valueTokens[2]);
                User value;

                if (isUsernameStaff(key)) {
                    value = new Staff(userCredentials);
                } else {
                    // valueTokens[3] = [UP 2017, IT 2017] || []

                    if (valueTokens[3].equals("[]")) {
                        value = new Customer(userCredentials, new ArrayList<>());
                    } else {
                        ArrayList<Booking> bookings = new ArrayList<>();
                        String[] removeParen = valueTokens[3].split(Pattern.quote("[]"), 0);
                        String[] allBookings = removeParen[0].split(", ");

                        for (String i : allBookings) {
                            String[] indiBooking = i.split(" ");
                            Booking booking = new Booking(indiBooking[0], LocalDateTime.parse(indiBooking[1]));
                            bookings.add(booking);
                        }

                        value = new Customer(userCredentials, bookings);
                    }
                }

                userDetails.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFilmDB() {

        String fileName = "films.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {
                // Reads one line at a time

                String[] tokens = line.split("=");

                String key = tokens[0];

                String[] valueTokens = tokens[1].split(", ");

                Film value = new Film(valueTokens[0], valueTokens[1], valueTokens[2], Integer.parseInt(valueTokens[3]));

                films.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
