package cinemaclub.database;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Screen;
import cinemaclub.cinema.Showing;
import cinemaclub.user.Customer;
import cinemaclub.user.Staff;
import cinemaclub.user.UserCredentials;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBase implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private static DataBase ourInstance = readExternalDB();

    private static final String fileName = "DB.txt";

    private UserRepository userRepository = new UserRepository(this);
    private FilmRepository filmRepository  = new FilmRepository(this);
    private ScreenRepository screenRepository = new ScreenRepository(this);

    public static UserRepository getUserRepository() {
        return ourInstance.userRepository;
    }

    public static ScreenRepository getScreenRepository() {
        return ourInstance.screenRepository;
    }

    public static FilmRepository getFilmRepository() {
        return ourInstance.filmRepository;
    }

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

            dataBase.userRepository.addStaffID("1", "noStaff");
            dataBase.userRepository.addStaffID("2", "noStaff");
            dataBase.userRepository.addStaffID("3", "noStaff");
            dataBase.userRepository.addStaffID("4", "noStaff");
            dataBase.userRepository.addStaffID("5", "noStaff");

            Film up = new Film("UP", "/UP.jpg", "A great film", "02:00");
            Film walle = new Film("Walle", "/walle.jpg", "A another great film", "02:00");

            dataBase.filmRepository.addFilm("UP", up);
            dataBase.filmRepository.addFilm("Walle", walle);

            Screen screen1 = new Screen(1, 5, 10);
            Screen screen2 = new Screen(2, 8, 10);
            Screen screen3 = new Screen(3, 5, 5);
            Screen screen4 = new Screen(4, 12, 14);
            Screen screen5 = new Screen(5, 12, 1);

            dataBase.screenRepository.addScreen(screen1);
            dataBase.screenRepository.addScreen(screen2);
            dataBase.screenRepository.addScreen(screen3);
            dataBase.screenRepository.addScreen(screen4);
            dataBase.screenRepository.addScreen(screen5);

            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2017-12-15", "13:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2017-12-15", "12:00", walle, new HashMap<>()));

            dataBase.userRepository.addUser("c", new Customer(new UserCredentials("c", "c@c.com", "c", "Customer", "Tester"), new ArrayList<>()));

            Staff staff = new Staff(new UserCredentials("sally", "sally@cinemaclub.com", "s", "Sally", "Table"));
            Staff staff2 = new Staff(new UserCredentials("sam", "sam@cinemaclub.com", "samrocks", "Sam", "Tarly"));
            Staff staff3 = new Staff(new UserCredentials("ghita", "ghita@cinemaclub.com", "ghitarocks", "Ghita", "Mostefaoui"));

            staff.setStaffId("1");
            staff2.setStaffId("2");
            staff3.setStaffId("3");
            dataBase.userRepository.assignStaffID("1", "sally");
            dataBase.userRepository.assignStaffID("2", "sam");
            dataBase.userRepository.assignStaffID("3", "ghita");
            dataBase.userRepository.addUser("sally", staff);
            dataBase.userRepository.addUser("sam", staff2);
            dataBase.userRepository.addUser("ghita", staff3);
        }
        return dataBase;
    }
}
