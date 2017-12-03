package cinemaclub.database;

import cinemaclub.cinema.Film;
import cinemaclub.user.Customer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataBase implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private static DataBase ourInstance = readExternalDB();

    private static final String fileName = "DB.txt";

    private UserRepository userRepository = new UserRepository(this);
    private ScreenRepository screenRepository = new ScreenRepository(this);

    private Map<String, Film> films = new HashMap<>();

    public static DataBase getInstance() {
        return ourInstance;
    }

    public static UserRepository getUserRepository() {
        return ourInstance.userRepository;
    }

    public static ScreenRepository getScreenRepository() {
        return ourInstance.screenRepository;
    }

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

    //WRITING TO EXTERNAL DATABASE TXT FILE
    void updateExternalDB() {
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
