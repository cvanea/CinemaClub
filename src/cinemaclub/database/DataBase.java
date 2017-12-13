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
            dataBase.userRepository.addStaffID("6", "noStaff");

            Film up = new Film("UP", "/UP.jpg", "Seventy-eight year old Carl Fredricksen travels to Paradise Falls in his home equipped with balloons, inadvertently taking a young stowaway.", "02:00");
            Film walle = new Film("WALL·E", "/walle.jpg", "In the distant future, a small waste-collecting robot inadvertently embarks on a space journey that will ultimately decide the fate of mankind.", "02:00");
            Film findingNemo = new Film("Finding Nemo", "/FindingNemo.jpg", "After his son is captured in the Great Barrier Reef and taken to Sydney, a timid clownfish sets out on a journey to bring him home.", "02:00");
            Film toyStory = new Film("Toy Story", "/ToyStory.jpg", "A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy's room.", "02:00");
            Film princessMononoke = new Film("Princess Mononoke", "/PrincessMononoke.jpg", "On a journey to find the cure for a Tatarigami's curse, Ashitaka finds himself in the middle of a war between the forest gods and Tatara, a mining colony. In this quest he also meets San, the Mononoke Hime.", "02:00");
            Film spiritedAway = new Film("Spirited Away", "/SpiritedAway.jpg", "During her family's move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches, and spirits, and where humans are changed into beasts.", "02:00");
            Film howlsMovingCastle = new Film("Howl's Moving Castle", "/HowlsMovingCastle.jpg", "When an unconfident young woman is cursed with an old body by a spiteful witch, her only chance of breaking the spell lies with a self-indulgent yet insecure young wizard and his companions in his legged, walking castle.", "02:00");
            Film theIncredibles = new Film("The Incredibles", "/incredibles.jpg", "A family of undercover superheroes, while trying to live the quiet suburban life, are forced into action to save the world.", "01:55");
            Film monsterInc = new Film("Monsters, Inc", "/monstersinc.jpg", "In order to power the city, monsters have to scare children so that they scream. However, the children are toxic to the monsters, and after a child gets through, 2 monsters realize things may not be what they think.", "01:32");
            Film monstersUni = new Film("Monsters University", "/monstersuni.jpg", "A look at the relationship between Mike and Sulley during their days at Monsters University -- when they weren't necessarily the best of friends.", "01:44");
            Film toyStory2 = new Film("Toy Story 2", "/toystory2.jpg", "When Woody is stolen by a toy collector, Buzz and his friends vow to rescue him, but Woody finds the idea of immortality in a museum tempting.", "01:32");

            dataBase.filmRepository.addFilm("UP", up);
            dataBase.filmRepository.addFilm("WALL·E", walle);
            dataBase.filmRepository.addFilm("Finding Nemo", findingNemo);
            dataBase.filmRepository.addFilm("Toy Story", toyStory);
            dataBase.filmRepository.addFilm("Toy Story 2", toyStory2);
            dataBase.filmRepository.addFilm("Princess Mononoke", princessMononoke);
            dataBase.filmRepository.addFilm("Spirited Away", spiritedAway);
            dataBase.filmRepository.addFilm("Howl's Moving Castle", howlsMovingCastle);
            dataBase.filmRepository.addFilm("The Incredibles", theIncredibles);
            dataBase.filmRepository.addFilm("Monsters, Inc", monsterInc);
            dataBase.filmRepository.addFilm("Monsters University", monstersUni);

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
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2017-12-16", "13:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2017-12-17", "13:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2017-12-18", "13:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2017-12-19", "13:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2017-12-20", "13:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2017-12-21", "13:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2017-12-22", "13:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2017-12-23", "13:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-03", "13:00", up, new HashMap<>()));

            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2017-12-15", "12:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2017-12-15", "15:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2017-12-16", "12:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2017-12-16", "15:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2017-12-17", "12:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2017-12-17", "15:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2017-12-18", "12:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2017-12-18", "15:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2017-12-19", "12:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2017-12-19", "15:00", walle, new HashMap<>()));

            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2017-01-05", "10:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2017-01-05", "09:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2017-01-05", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2017-01-06", "10:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2017-01-06", "09:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2017-01-06", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2017-01-07", "10:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2017-01-07", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2017-01-08", "10:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2017-01-08", "18:00", findingNemo, new HashMap<>()));

            dataBase.userRepository.addUser("claudia", new Customer(new UserCredentials("claudia", "claudia@hotmail.co.uk", "claudia", "Claudia", "Vanea"), new ArrayList<>()));
            dataBase.userRepository.addUser("alex", new Customer(new UserCredentials("alex", "alex@hotmail.co.uk", "alex", "Alex", "Charles"), new ArrayList<>()));
            dataBase.userRepository.addUser("tom", new Customer(new UserCredentials("tom", "tom@hotmail.co.uk", "tom", "Tom", "Firth"), new ArrayList<>()));
            dataBase.userRepository.addUser("giulia", new Customer(new UserCredentials("giulia", "giulia@hotmail.co.uk", "giulia", "Giulia", "Toti"), new ArrayList<>()));

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
