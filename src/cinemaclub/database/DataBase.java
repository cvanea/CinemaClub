package cinemaclub.database;

import cinemaclub.cinema.Film;
import cinemaclub.user.Customer;
import cinemaclub.user.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataBase implements Serializable {
    private static DataBase ourInstance = readExternalDB();

    private static final String fileName = "DB.txt";

    private Map<String, String> staffIDs = new HashMap<>();
    private Map<String, User> userDetails = new HashMap<>();
    private Map<String, Film> films = new HashMap<>();

    public static DataBase getInstance() {
        return ourInstance;
    }

    //STAFFID
    public void addStaffID(String staffId, String username) {

        staffIDs.put(staffId, username);

        updateExternalDB();
    }

    public void assignStaffID(String staffId, String username) {

        staffIDs.put(staffId, username);

        updateExternalDB();
    }

    public String getStaffIDValue(String staffId) {

        return staffIDs.get(staffId);

    }
    //END OF STAFFID

    //USER
    private Boolean isUsernameStaff(String username) {

        return staffIDs.containsValue(username);
    }

    public void writeToUserDetails(String username, User user) {

        userDetails.put(username, user);

        updateExternalDB();
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

        updateExternalDB();
    }

    public void setEmail(User user) {
        userDetails.put(user.getUsername(), user);

        updateExternalDB();
    }

    public void setPassword(User user) {
        userDetails.put(user.getUsername(), user);

        updateExternalDB();
    }

    public void deleteUser(String username) {
        userDetails.remove(username);

        updateExternalDB();
    }
    //END OF USER

    //FILMS
    public void addFilm(String title, Film film) {
        films.put(title, film);

        updateExternalDB();
    }

    public Boolean checkForFilm(String title) {
        return films.containsKey(title);
    }

    public Film getFilm(String title) {
        return films.get(title);
    }

    public void setFilmTitle(String oldTitle, String newTitle, Film film) {
        films.remove(oldTitle);
        films.put(newTitle, film);

        updateExternalDB();
    }

    public void setFilmImagePath(Film film) {
        films.put(film.getTitle(), film);

        updateExternalDB();
    }

    public void setFilmDescription(Film film) {
        films.put(film.getTitle(), film);

        updateExternalDB();
    }

    public void setFilmRunTime(Film film) {
        films.put(film.getTitle(), film);

        updateExternalDB();
    }

    public void deleteFilm(String title) {
        films.remove(title);

        updateExternalDB();
    }
    //END OF FILMS

    //SCREEN
    public Boolean noExistingBooking(Customer customer) {
        return customer.getBookings().isEmpty();
    }
    //END OF SCREEN

    public void printUserDatabase() {
        for (Map.Entry entry : userDetails.entrySet()) {
            System.out.print(entry.toString() + "\n");
        }
    }

    //WRITING TO EXTERNAL DATABASE TXT FILE
    private void updateExternalDB() {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //END OF WRITING TO EXTERNAL

    //READING FROM EXTERNAL
    private static DataBase readExternalDB() {
        DataBase dataBase;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            dataBase = (DataBase) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            dataBase = new DataBase();
        }
        return dataBase;
    }
    //END OF READING EXTERNAL
}
