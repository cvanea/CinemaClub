package cinemaclub.cinema;

import exceptions.*;

import java.util.Scanner;

/**
 * Class for system integration testing.
 * Allowed for fast testing of the successful or unsuccessful interaction of the various cinema systems.
 */
public class MainTester {

    /**
     * Driver method for the tester.
     *
     * @param args no arguments by default
     */
    public static void main(String[] args) {

        registerTester();
//        loginTester();
//        logoutTester();
//        profileTester();
//        bookingTester();
//        filmEditTester();
//        showingsTester();
//        seatsTester();
//        exportTester();
//        screenTester();
//        timeTester();
    }

    /**
     * Using simple cli interaction, this tests whether a user can login.
     */
    private static void loginTester() {
        Cinema cinema = new Cinema();

        while (true) {
            try {
                cinema.loginUser(inputData("username"), inputData("password"));
                System.exit(0);
            } catch (UserDetailsDoNotExistException | UserDetailsIncorrectException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Using simple sli interaction, this tests whether a user can register.
     */
    private static void registerTester() {
        Scanner input = new Scanner(System.in);
        Cinema cinema = new Cinema();

        while (true) {
            try {
                System.out.println("Are you staff? ");

                if (input.nextLine().equals("y")) {
                    cinema.registerUser(inputData("username"), inputData("email"), inputData("password"),
                        inputData("first name"), inputData("surname"), "staff", inputData("staffID"));

                } else {
                    cinema.registerUser(inputData("username"), inputData("email"), inputData("password"), inputData("first name"), inputData("surname"), "customer", null);
                }

                System.exit(0);
            } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException | EmptyUserInputException | NotValidEmailException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * This tests whether a user can be registered, logged in, and logged out successfully.
     */
    private static void logoutTester() {
        Cinema cinema = new Cinema();

        cinema.deleteUser("LogoutTester1");
        cinema.deleteUser("LogoutTester2");

        try {
            cinema.registerUser("LogoutTester1", "logouttest1@tester.com", "pass1", "Logout", "Tester", "customer", null);
        } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException | EmptyUserInputException | NotValidEmailException e) {
            System.out.println(e.getMessage() + "1");
        }

        try {
            cinema.registerUser("LogoutTester2", "logouttester2@tester.com", "pass2", "Logout2", "Tester2","customer", null);
        } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException | EmptyUserInputException | NotValidEmailException e) {
            System.out.println(e.getMessage() + "1");
        }

        try {
            cinema.loginUser("LogoutTester1",  "pass1");
        } catch (UserDetailsDoNotExistException | UserDetailsIncorrectException e) {
            System.out.println(e.getMessage() + "2");
        }

        System.out.println(cinema.getCurrentUser());

        cinema.logoutCurrentUser();

        try {
            cinema.loginUser("LogoutTester2", "pass2");
        } catch (UserDetailsDoNotExistException | UserDetailsIncorrectException e) {
            System.out.println(e.getMessage() + "2");
        }

        System.out.println(cinema.getCurrentUser());
    }

    /**
     * Small scanner cli interaction for login and register testers.
     *
     * @param data particular data requests
     * @return user cli input
     */
    private static String inputData(String data) {
        // Returns inputs as a string
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your " + data + ": ");

        return input.next();
    }

    /**
     * Tests whether a user's details can be queried and changed.
     */
    private static void profileTester() {
        Cinema cinema = new Cinema();

        cinema.deleteUser("ProfileTester");
        cinema.deleteUser("NewTester");

        try {
            cinema.registerUser("ProfileTester", "test@tester.com", "pass", "Profile", "Tester", "customer", null);
        } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException | EmptyUserInputException | NotValidEmailException e) {
            System.out.println(e.getMessage() + "1");
        }

        try {
            cinema.loginUser("ProfileTester",  "pass");
        } catch (UserDetailsDoNotExistException | UserDetailsIncorrectException e) {
            System.out.println(e.getMessage() + "2");
        }

//        try {
//            cinema.loginUser("ProfileTester", "newtester@tester.com", "newpass");
//        } catch (UserDetailsDoNotExistException | UserDetailsIncorrectException e) {
//            System.out.println(e.getMessage() + "3");
//        }

        System.out.println(cinema.getProfileDetails().getUsername());
        System.out.println(cinema.getProfileDetails().getEmail());
        System.out.println(cinema.getProfileDetails().getPassword());

        try {
            cinema.setUsername("NewTester");
            cinema.setUserEmail("newtester@tester.com");
            cinema.setUserPassword("newpass");
        } catch (UsernameTakenException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(cinema.getProfileDetails().getUsername());
        System.out.println(cinema.getProfileDetails().getEmail());
        System.out.println(cinema.getProfileDetails().getPassword());

    }

    /**
     * Tests whether a showing can be added to the cinema, queried, added to a user's bookings, and have user's bookings queried.
     */
    private static void bookingTester() {
        Cinema cinema = new Cinema();

        cinema.deleteUser("BookingTest");

        try {
            cinema.registerUser("BookingTest", "booking@booking.com", "bookingpass", "Booking", "Tester", "customer", null);
        } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException | EmptyUserInputException | NotValidEmailException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        try {
            cinema.loginUser("BookingTest",  "bookingpass");
        } catch (UserDetailsDoNotExistException | UserDetailsIncorrectException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        Film filmTest = new Film("FilmTest", "Path", "A tester film", "01:00");
        Film filmTest2 = new Film("FilmTest2", "Path2", "A tester film2", "01:00");

        try {
            cinema.addShowing(cinema.getScreen(1),"2018-11-09", "15:00", filmTest);
            cinema.addShowing(cinema.getScreen(1),"2018-11-09", "12:00", filmTest2);
        } catch (ShowingAlreadyExistsException | ShowingOnOtherScreenException | OverlappingRuntimeException e) {
            System.out.println(e.getMessage());
        }

        Showing testShowing = cinema.getShowingByDateTimeScreen(cinema.getScreen(1),"2018-11-09", "15:00");
        Showing testShowing2 = cinema.getShowingByDateTimeScreen(cinema.getScreen(1),"2018-11-09", "12:00");

        try {
            cinema.bookFilm(testShowing, "A", 5);
            cinema.bookFilm(testShowing2, "B", 7);
        } catch (SeatAlreadyTakenException | SeatNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(cinema.getPastBookingsHistory());
        } catch (NoBookingsException | NoPastBookingsException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(cinema.getFutureBookingsHistory());
        } catch (NoBookingsException | NoFutureBookingsException e) {
            System.out.println(e.getMessage());
        }

        try {
            cinema.deleteFutureBooking(cinema.getBookingByTitleDateTime("FilmTest", "2018-11-09", "15:00"));
        } catch (NoFutureBookingsException | NotAFutureBookingException | NoBookingsException | SeatNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(cinema.getFutureBookingsHistory());
        } catch (NoBookingsException | NoFutureBookingsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests whether a film can be added, and updated, on the cinema.
     */
    private static void filmEditTester() {
        Cinema cinema = new Cinema();

        cinema.deleteFilm("Test");
        cinema.deleteFilm("UPTest");

        try {
            cinema.addFilm("Test", "Path", "A film about a man, a boy, and a dog", "01:00");
        } catch (FilmExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(cinema.getFilmByTitle("Test").getTitle());
        System.out.println(cinema.getFilmByTitle("Test").getImagePath());
        System.out.println(cinema.getFilmByTitle("Test").getRuntime());
        System.out.println(cinema.getFilmByTitle("Test").getDescription());

        try {
            cinema.setFilmTitle(cinema.getFilmByTitle("Test"), "UPTest");
            cinema.setFilmImagePath(cinema.getFilmByTitle("UPTest"), "PathTest");
            cinema.setFilmRunTime(cinema.getFilmByTitle("UPTest"), "02:00");
            cinema.setFilmDescription(cinema.getFilmByTitle("UPTest"), "DescriptionTest");
        } catch (FilmExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(cinema.getFilmByTitle("UPTest").getTitle());
        System.out.println(cinema.getFilmByTitle("UPTest").getImagePath());
        System.out.println(cinema.getFilmByTitle("UPTest").getRuntime());
        System.out.println(cinema.getFilmByTitle("UPTest").getDescription());
    }

    /**
     * Tests whether a newly added film can be adding to showings.
     */
    private static void showingsTester() {
        Cinema cinema = new Cinema();

        try {
            cinema.addFilm("Tester", "testpath", "testing showings", "01:00");
        } catch (FilmExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(cinema.getFilmByTitle("Tester"));

        try {
            cinema.addShowing(cinema.getScreen(1), "2018-02-20", "12:00", cinema.getFilmByTitle("Tester"));
        } catch (ShowingAlreadyExistsException | ShowingOnOtherScreenException | OverlappingRuntimeException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(cinema.getShowingsByDate("2018-02-20", cinema.getScreen(1)));
        } catch (PastDateException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests that seats had been correctly calculated on a screen.
     */
    private static void seatsTester() {
        Cinema cinema = new Cinema();

        Screen screen = cinema.getScreen(1);

        System.out.println(screen.getSeats());
    }

    /**
     * Tests the export to csv functionality.
     */
    private static void exportTester() {
        Cinema cinema = new Cinema();

        cinema.exportShowingsToCsv();
    }

    /**
     * Tests whether screens can be added, along with films and showings on that new screen.
     */
    private static void screenTester() {
        Cinema cinema = new Cinema();

        Screen screenTest = new Screen(2, 4, 5);

        try {
            cinema.addScreen(screenTest);
        } catch (ScreenNumberAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(cinema.getScreen(1));
        System.out.println(cinema.getScreen(2));

        try {
            cinema.addFilm("Tester", "testpath", "testing showings", "01:00");
        } catch (FilmExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(cinema.getFilmByTitle("Tester"));

        try {
            cinema.addShowing(cinema.getScreen(2), "2018-02-20", "12:00", cinema.getFilmByTitle("Tester"));
        } catch (ShowingAlreadyExistsException | ShowingOnOtherScreenException | OverlappingRuntimeException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(cinema.getShowingsByDate("2018-02-20", cinema.getScreen(2)));
        } catch (PastDateException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(cinema.getShowingsByDate("2018-02-20", cinema.getScreen(1)));
        } catch (PastDateException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests the validator for checking a valid time format.
     */
    private static void timeTester() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter time: ");

        String time = scanner.next();

        String[] splitTime = time.split("(?!^)");

        if (splitTime.length != 5) {
            System.out.println("error1");
        }

        try {
            Integer.parseInt(splitTime[0]);
            Integer.parseInt(splitTime[1]);
            Integer.parseInt(splitTime[3]);
            Integer.parseInt(splitTime[4]);
        } catch (NumberFormatException e) {
            System.out.println("error2");
        }

        if (!splitTime[2].equals(":")) {
            System.out.println("error3");
        }

        if (Integer.parseInt(splitTime[0] + splitTime[1]) > 24) {
            System.out.println("error4");
        }

        if (Integer.parseInt(splitTime[0] + splitTime[1]) == 24 && (Integer.parseInt(splitTime[3]) != 0 || Integer.parseInt(splitTime[4]) != 0)) {
            System.out.println("error5");
        }

        if (Integer.parseInt(splitTime[3] + splitTime[4]) > 60) {
            System.out.println("error6");
        }
    }
}