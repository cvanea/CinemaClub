package cinemaclub.database;

import java.io.*;

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
            //TODO INITIAL DEFAULTS FOR THE CINEMA GO HERE
            dataBase = new DataBase();

//            userRepository.addStaffID("1", "noStaff");
//            userRepository.addStaffID("2", "noStaff");
//            userRepository.addStaffID("3", "noStaff");
//            userRepository.addStaffID("4", "noStaff");
//            userRepository.addStaffID("5", "noStaff");
//
//            Film up = new Film("UP", "/UP.jpg", "A great film", "01:00");
//            Film walle = new Film("Walle", "/walle.jpg", "A another great film", "02:00");
//
//            filmRepository.addFilm("UP", up);
//            filmRepository.addFilm("Walle", walle);
//
//            Screen screen1 = new Screen(1, 5, 10);
//
//            screenRepository.addScreen(screen1);
//
//            screenRepository.addShowing(screen1, "2017-12-15", "13:00", up);
//            screenRepository.addShowing(screen1, "2017-12-15", "11:00", walle);

        }
        return dataBase;
    }

}
