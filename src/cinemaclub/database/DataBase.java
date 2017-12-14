package cinemaclub.database;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Screen;
import cinemaclub.cinema.Showing;
import cinemaclub.user.Booking;
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

            Film up = new Film("UP", "/UP.jpg", "Seventy-eight year old Carl Fredricksen travels to Paradise Falls in his home equipped with balloons, inadvertently taking a young stowaway.", "01:36");
            Film walle = new Film("WALL·E", "/walle.jpg", "In the distant future, a small waste-collecting robot inadvertently embarks on a space journey that will ultimately decide the fate of mankind.", "01:28");
            Film findingNemo = new Film("Finding Nemo", "/FindingNemo.jpg", "After his son is captured in the Great Barrier Reef and taken to Sydney, a timid clownfish sets out on a journey to bring him home.", "01:40");
            Film toyStory = new Film("Toy Story", "/toystory.jpg", "A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy's room.", "01:21");
            Film princessMononoke = new Film("Princess Mononoke", "/PrincessMononoke.jpg", "On a journey to find the cure for a Tatarigami's curse, Ashitaka finds himself in the middle of a war between the forest gods and Tatara, a mining colony. In this quest he also meets San, the Mononoke Hime.", "02:14");
            Film spiritedAway = new Film("Spirited Away", "/SpiritedAway.jpg", "During her family's move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches, and spirits, and where humans are changed into beasts.", "02:05");
            Film howlsMovingCastle = new Film("Howl's Moving Castle", "/HowlsMovingCastle.jpg", "When an unconfident young woman is cursed with an old body by a spiteful witch, her only chance of breaking the spell lies with a self-indulgent yet insecure young wizard and his companions in his legged, walking castle.", "01:59");
            Film theIncredibles = new Film("The Incredibles", "/incred.jpg", "A family of undercover superheroes, while trying to live the quiet suburban life, are forced into action to save the world.", "01:55");
            Film monsterInc = new Film("Monsters, Inc", "/monsterinc.jpg", "In order to power the city, monsters have to scare children so that they scream. However, the children are toxic to the monsters, and after a child gets through, 2 monsters realize things may not be what they think.", "01:32");
            Film monstersUni = new Film("Monsters University", "/monstersuni.jpg", "A look at the relationship between Mike and Sulley during their days at Monsters University -- when they weren't necessarily the best of friends.", "01:44");
            Film toyStory2 = new Film("Toy Story 2", "/ToyStory2.jpg", "When Woody is stolen by a toy collector, Buzz and his friends vow to rescue him, but Woody finds the idea of immortality in a museum tempting.", "01:32");
            Film ratatouille = new Film("Ratatouille", "/1ratatouille.jpg", "A rat who can cook makes an unusual alliance with a young kitchen worker at a famous restaurant.", "01:51");
            Film kungFuPanda = new Film("Kung Fu Panda", "/kunfupnd.jpg", "The Dragon Warrior has to clash against the savage Tai Lung as China's fate hangs in the balance: However, the Dragon Warrior mantle is supposedly mistaken to be bestowed upon an obese panda who is a tyro in martial arts.", "01:32");
            Film iceAge = new Film("Ice Age", "/iceage1.jpg", "Set during the Ice Age, a sabertooth tiger, a sloth, and a wooly mammoth find a lost human infant, and they try to return him to his tribe.", "01:21");

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
            dataBase.filmRepository.addFilm("Ratatouille", ratatouille);
            dataBase.filmRepository.addFilm("Ice Age", iceAge);
            dataBase.filmRepository.addFilm("Kung Fu Panda", kungFuPanda);

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


            // Past Showing
            Showing showing1 = new Showing(screen1, "2017-12-10", "06:00", toyStory, new HashMap<>());
            Showing showing2 = new Showing(screen1, "2017-12-10", "08:00", princessMononoke, new HashMap<>());
            Showing showing3 = new Showing(screen1, "2017-12-10", "10:00", spiritedAway, new HashMap<>());
            Showing showing4 = new Showing(screen1, "2017-12-10", "12:00", theIncredibles, new HashMap<>());
            Showing showing5 = new Showing(screen1, "2017-12-10", "14:00", up, new HashMap<>());
            Showing showing6 = new Showing(screen1, "2017-12-10", "16:00", theIncredibles, new HashMap<>());
            Showing showing7 = new Showing(screen1, "2017-12-10", "18:00", iceAge, new HashMap<>());
            Showing showing8 = new Showing(screen1, "2017-12-10", "20:00", ratatouille, new HashMap<>());
            Showing showing9 = new Showing(screen1, "2017-12-10", "22:00", walle, new HashMap<>());

            dataBase.screenRepository.addShowing(screen1, showing1);
            dataBase.screenRepository.addShowing(screen1, showing2);
            dataBase.screenRepository.addShowing(screen1, showing3);
            dataBase.screenRepository.addShowing(screen1, showing4);
            dataBase.screenRepository.addShowing(screen1, showing5);
            dataBase.screenRepository.addShowing(screen1, showing6);
            dataBase.screenRepository.addShowing(screen1, showing7);
            dataBase.screenRepository.addShowing(screen1, showing8);
            dataBase.screenRepository.addShowing(screen1, showing9);

            // 2018-01-01
            // screen 1
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-01", "06:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-01", "08:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-01", "10:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-01", "12:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-01", "14:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-01", "16:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-01", "18:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-01", "20:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-01", "22:00", walle, new HashMap<>()));
            // screen 2
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-01", "06:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-01", "08:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-01", "10:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-01", "12:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-01", "14:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-01", "16:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-01", "18:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-01", "20:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-01", "22:00", theIncredibles, new HashMap<>()));
            // screen 3
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-01", "06:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-01", "08:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-01", "10:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-01", "12:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-01", "14:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-01", "16:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-01", "18:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-01", "20:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-01", "22:00", spiritedAway, new HashMap<>()));
            // screen 4
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-01", "06:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-01", "08:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-01", "10:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-01", "12:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-01", "14:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-01", "16:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-01", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-01", "20:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-01", "22:00", ratatouille, new HashMap<>()));
            // screen 5
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-01", "06:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-01", "08:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-01", "10:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-01", "12:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-01", "14:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-01", "16:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-01", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-01", "20:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-01", "22:00", iceAge, new HashMap<>()));

       // 2018-01-02
            // screen 1
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-02", "06:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-02", "08:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-02", "10:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-02", "12:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-02", "14:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-02", "16:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-02", "18:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-02", "20:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-02", "22:00", walle, new HashMap<>()));
            // screen 2
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-02", "06:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-02", "08:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-02", "10:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-02", "12:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-02", "14:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-02", "16:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-02", "18:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-02", "20:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-02", "22:00", theIncredibles, new HashMap<>()));
            // screen 3
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-02", "06:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-02", "08:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-02", "10:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-02", "12:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-02", "14:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-02", "16:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-02", "18:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-02", "20:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-02", "22:00", spiritedAway, new HashMap<>()));
            // screen 4
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-02", "06:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-02", "08:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-02", "10:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-02", "12:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-02", "14:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-02", "16:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-02", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-02", "20:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-02", "22:00", ratatouille, new HashMap<>()));
            // screen 5
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-02", "06:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-02", "08:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-02", "10:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-02", "12:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-02", "14:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-02", "16:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-02", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-02", "20:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-02", "22:00", iceAge, new HashMap<>()));

            // 2018-01-03
            // screen 3
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-03", "06:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-03", "08:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-03", "10:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-03", "12:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-03", "14:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-03", "16:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-03", "18:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-03", "20:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-03", "22:00", walle, new HashMap<>()));
            // screen 2
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-03", "06:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-03", "08:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-03", "10:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-03", "12:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-03", "14:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-03", "16:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-03", "18:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-03", "20:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-03", "22:00", theIncredibles, new HashMap<>()));
            // screen 3
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-03", "06:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-03", "08:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-03", "10:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-03", "12:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-03", "14:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-03", "16:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-03", "18:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-03", "20:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-03", "22:00", spiritedAway, new HashMap<>()));
            // screen 4
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-03", "06:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-03", "08:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-03", "10:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-03", "12:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-03", "14:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-03", "16:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-03", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-03", "20:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-03", "22:00", ratatouille, new HashMap<>()));
            // screen 5
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-03", "06:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-03", "08:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-03", "10:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-03", "12:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-03", "14:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-03", "16:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-03", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-03", "20:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-03", "22:00", iceAge, new HashMap<>()));

            // 2018-01-03
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-04", "06:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-04", "08:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-04", "12:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-04", "10:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-04", "14:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-04", "16:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-04", "18:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-04", "20:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-04", "22:00", walle, new HashMap<>()));
            // screen 2
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-04", "06:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-04", "08:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-04", "10:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-04", "12:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-04", "14:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-04", "16:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-04", "18:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-04", "20:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-04", "22:00", theIncredibles, new HashMap<>()));
            // screen 3
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-04", "06:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-04", "08:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-04", "10:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-04", "12:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-04", "14:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-04", "16:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-04", "18:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-04", "20:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-04", "22:00", spiritedAway, new HashMap<>()));
            // screen 4
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-04", "06:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-04", "08:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-04", "10:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-04", "12:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-04", "14:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-04", "16:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-04", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-04", "20:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-04", "22:00", ratatouille, new HashMap<>()));
            // screen 5
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-04", "06:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-04", "08:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-04", "10:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-04", "12:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-04", "14:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-04", "16:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-04", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-04", "20:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-04", "22:00", iceAge, new HashMap<>()));


            // 2018-01-05
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-05", "06:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-05", "08:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-05", "12:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-05", "10:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-05", "14:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-05", "16:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-05", "18:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-05", "20:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-05", "22:00", walle, new HashMap<>()));
            // screen 5
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-05", "06:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-05", "08:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-05", "10:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-05", "12:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-05", "14:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-05", "16:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-05", "18:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-05", "20:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-05", "22:00", theIncredibles, new HashMap<>()));
            // screen 5
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-05", "06:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-05", "08:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-05", "10:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-05", "12:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-05", "14:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-05", "16:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-05", "18:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-05", "20:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-05", "22:00", spiritedAway, new HashMap<>()));
            // screen 5
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-05", "06:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-05", "08:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-05", "10:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-05", "12:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-05", "14:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-05", "16:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-05", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-05", "20:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-05", "22:00", ratatouille, new HashMap<>()));
            // screen 5
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-05", "06:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-05", "08:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-05", "10:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-05", "12:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-05", "14:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-05", "16:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-05", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-05", "20:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-05", "22:00", iceAge, new HashMap<>()));

            // 2018-01-06
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-06", "06:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-06", "08:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-06", "12:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-06", "10:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-06", "14:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-06", "16:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-06", "18:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-06", "20:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-06", "22:00", walle, new HashMap<>()));
            // screen 6
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-06", "06:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-06", "08:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-06", "10:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-06", "12:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-06", "14:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-06", "16:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-06", "18:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-06", "20:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-06", "22:00", theIncredibles, new HashMap<>()));
            // screen 6
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-06", "06:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-06", "08:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-06", "10:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-06", "12:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-06", "14:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-06", "16:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-06", "18:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-06", "20:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-06", "22:00", spiritedAway, new HashMap<>()));
            // screen 6
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-06", "06:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-06", "08:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-06", "10:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-06", "12:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-06", "14:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-06", "16:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-06", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-06", "20:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-06", "22:00", ratatouille, new HashMap<>()));
            // screen 6
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-06", "06:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-06", "08:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-06", "10:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-06", "12:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-06", "14:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-06", "16:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-06", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-06", "20:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-06", "22:00", iceAge, new HashMap<>()));


            // 2018-01-07
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-07", "06:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-07", "08:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-07", "12:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-07", "10:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-07", "14:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-07", "16:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-07", "18:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-07", "20:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-07", "22:00", walle, new HashMap<>()));
            // screen 7
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-07", "06:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-07", "08:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-07", "10:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-07", "12:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-07", "14:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-07", "16:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-07", "18:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-07", "20:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-07", "22:00", theIncredibles, new HashMap<>()));
            // screen 7
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-07", "06:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-07", "08:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-07", "10:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-07", "12:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-07", "14:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-07", "16:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-07", "18:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-07", "20:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-07", "22:00", spiritedAway, new HashMap<>()));
            // screen 7
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-07", "06:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-07", "08:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-07", "10:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-07", "12:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-07", "14:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-07", "16:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-07", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-07", "20:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-07", "22:00", ratatouille, new HashMap<>()));
            // screen 7
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-07", "06:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-07", "08:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-07", "10:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-07", "12:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-07", "14:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-07", "16:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-07", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-07", "20:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-07", "22:00", iceAge, new HashMap<>()));

            // 2018-01-08
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-08", "06:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-08", "08:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-08", "12:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-08", "10:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-08", "14:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-08", "16:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-08", "18:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-08", "20:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-08", "22:00", walle, new HashMap<>()));
            // screen 8
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-08", "06:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-08", "08:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-08", "10:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-08", "12:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-08", "14:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-08", "16:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-08", "18:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-08", "20:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-08", "22:00", theIncredibles, new HashMap<>()));
            // screen 8
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-08", "06:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-08", "08:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-08", "10:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-08", "12:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-08", "14:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-08", "16:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-08", "18:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-08", "20:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-08", "22:00", spiritedAway, new HashMap<>()));
            // screen 8
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-08", "06:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-08", "08:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-08", "10:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-08", "12:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-08", "14:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-08", "16:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-08", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-08", "20:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-08", "22:00", ratatouille, new HashMap<>()));
            // screen 8
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-08", "06:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-08", "08:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-08", "10:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-08", "12:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-08", "14:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-08", "16:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-08", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-08", "20:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-08", "22:00", iceAge, new HashMap<>()));

            // 2018-01-09
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-09", "06:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-09", "08:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-09", "12:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-09", "10:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-09", "14:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-09", "16:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-09", "18:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-09", "20:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-09", "22:00", walle, new HashMap<>()));
            // screen 9
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-09", "06:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-09", "08:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-09", "10:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-09", "12:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-09", "14:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-09", "16:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-09", "18:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-09", "20:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-09", "22:00", theIncredibles, new HashMap<>()));
            // screen 9
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-09", "06:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-09", "08:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-09", "10:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-09", "12:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-09", "14:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-09", "16:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-09", "18:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-09", "20:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-09", "22:00", spiritedAway, new HashMap<>()));
            // screen 9
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-09", "06:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-09", "08:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-09", "10:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-09", "12:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-09", "14:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-09", "16:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-09", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-09", "20:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-09", "22:00", ratatouille, new HashMap<>()));
            // screen 9
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-09", "06:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-09", "08:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-09", "10:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-09", "12:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-09", "14:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-09", "16:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-09", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-09", "20:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-09", "22:00", iceAge, new HashMap<>()));

            // 2018-01-10
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-10", "06:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-10", "08:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-10", "12:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-10", "10:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-10", "14:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-10", "16:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-10", "18:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-10", "20:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-10", "22:00", walle, new HashMap<>()));
            // screen10
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-10", "06:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-10", "08:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-10", "10:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-10", "12:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-10", "14:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-10", "16:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-10", "18:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-10", "20:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-10", "22:00", theIncredibles, new HashMap<>()));
            // screen10
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-10", "06:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-10", "08:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-10", "10:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-10", "12:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-10", "14:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-10", "16:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-10", "18:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-10", "20:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-10", "22:00", spiritedAway, new HashMap<>()));
            // screen10
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-10", "06:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-10", "08:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-10", "10:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-10", "12:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-10", "14:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-10", "16:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-10", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-10", "20:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-10", "22:00", ratatouille, new HashMap<>()));
            // screen10
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-10", "06:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-10", "08:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-10", "10:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-10", "12:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-10", "14:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-10", "16:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-10", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-10", "20:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-10", "22:00", iceAge, new HashMap<>()));

            // 2018-01-11
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-11", "06:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-11", "08:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-11", "12:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-11", "10:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-11", "14:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-11", "16:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-11", "18:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-11", "20:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-11", "22:00", walle, new HashMap<>()));
            // screen11
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-11", "06:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-11", "08:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-11", "10:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-11", "12:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-11", "14:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-11", "16:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-11", "18:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-11", "20:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-11", "22:00", theIncredibles, new HashMap<>()));
            // screen11
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-11", "06:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-11", "08:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-11", "10:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-11", "12:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-11", "14:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-11", "16:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-11", "18:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-11", "20:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-11", "22:00", spiritedAway, new HashMap<>()));
            // screen11
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-11", "06:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-11", "08:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-11", "10:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-11", "12:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-11", "14:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-11", "16:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-11", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-11", "20:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-11", "22:00", ratatouille, new HashMap<>()));
            // screen11
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-11", "06:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-11", "08:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-11", "10:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-11", "12:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-11", "14:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-11", "16:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-11", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-11", "20:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-11", "22:00", iceAge, new HashMap<>()));

            // 2018-01-12
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-12", "06:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-12", "08:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-12", "12:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-12", "10:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-12", "14:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-12", "16:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-12", "18:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-12", "20:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-12", "22:00", walle, new HashMap<>()));
            // screen12
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-12", "06:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-12", "08:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-12", "10:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-12", "12:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-12", "14:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-12", "16:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-12", "18:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-12", "20:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-12", "22:00", theIncredibles, new HashMap<>()));
            // screen12
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-12", "06:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-12", "08:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-12", "10:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-12", "12:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-12", "14:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-12", "16:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-12", "18:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-12", "20:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-12", "22:00", spiritedAway, new HashMap<>()));
            // screen12
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-12", "06:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-12", "08:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-12", "10:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-12", "12:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-12", "14:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-12", "16:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-12", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-12", "20:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-12", "22:00", ratatouille, new HashMap<>()));
            // screen12
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-12", "06:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-12", "08:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-12", "10:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-12", "12:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-12", "14:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-12", "16:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-12", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-12", "20:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-12", "22:00", iceAge, new HashMap<>()));


            // 2018-01-13
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-13", "06:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-13", "08:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-13", "12:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-13", "10:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-13", "14:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-13", "16:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-13", "18:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-13", "20:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-13", "22:00", walle, new HashMap<>()));
            // screen13
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-13", "06:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-13", "08:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-13", "10:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-13", "12:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-13", "14:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-13", "16:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-13", "18:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-13", "20:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-13", "22:00", theIncredibles, new HashMap<>()));
            // screen13
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-13", "06:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-13", "08:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-13", "10:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-13", "12:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-13", "14:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-13", "16:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-13", "18:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-13", "20:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-13", "22:00", spiritedAway, new HashMap<>()));
            // screen13
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-13", "06:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-13", "08:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-13", "10:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-13", "12:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-13", "14:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-13", "16:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-13", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-13", "20:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-13", "22:00", ratatouille, new HashMap<>()));
            // screen13
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-13", "06:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-13", "08:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-13", "10:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-13", "12:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-13", "14:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-13", "16:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-13", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-13", "20:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-13", "22:00", iceAge, new HashMap<>()));

            // 2018-01-14
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-14", "06:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-14", "08:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-14", "12:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-14", "10:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-14", "14:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-14", "16:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-14", "18:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-14", "20:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen1, new Showing(screen1, "2018-01-14", "22:00", walle, new HashMap<>()));
            // screen14
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-14", "06:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-14", "08:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-14", "10:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-14", "12:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-14", "14:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-14", "16:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-14", "18:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-14", "20:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen2, new Showing(screen2, "2018-01-14", "22:00", theIncredibles, new HashMap<>()));
            // screen14
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-14", "06:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-14", "08:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-14", "10:00", toyStory2, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-14", "12:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-14", "14:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-14", "16:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-14", "18:00", ratatouille, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-14", "20:00", monsterInc, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen3, new Showing(screen3, "2018-01-14", "22:00", spiritedAway, new HashMap<>()));
            // screen14
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-14", "06:00", walle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-14", "08:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-14", "10:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-14", "12:00", spiritedAway, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-14", "14:00", theIncredibles, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-14", "16:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-14", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-14", "20:00", iceAge, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen4, new Showing(screen4, "2018-01-14", "22:00", ratatouille, new HashMap<>()));
            // screen14
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-14", "06:00", princessMononoke, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-14", "08:00", howlsMovingCastle, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-14", "10:00", toyStory, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-14", "12:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-14", "14:00", monstersUni, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-14", "16:00", kungFuPanda, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-14", "18:00", findingNemo, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-14", "20:00", up, new HashMap<>()));
            dataBase.screenRepository.addShowing(screen5, new Showing(screen5, "2018-01-14", "22:00", iceAge, new HashMap<>()));

            Customer customer1 = new Customer(new UserCredentials("claudia", "claudia@hotmail.co.uk", "claudia", "Claudia", "Vanea"), new ArrayList<>());

            Booking booking1 = new Booking(showing1, "B5");
            Booking booking2 = new Booking(showing5, "C7");
            Booking booking3 = new Booking(showing7, "A2");

            customer1.addBooking(booking1);
            customer1.addBooking(booking2);
            customer1.addBooking(booking3);

            dataBase.userRepository.addUser("claudia", customer1);
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
