package cinemaclub.cinema;

import exceptions.*;

import java.util.Scanner;

public class MainTester {

    //System integration testing.
    public static void main(String[] args) {

//        registerTester();
        loginTester();
//        logoutTester();
//        profileTester();
//        bookingTester();
//        filmEditTester();
//        seatsTester();

    }

    private static void loginTester() {
        Cinema cinema = new Cinema();

        while (true) {
            try {
                cinema.loginUser(inputData("username"), inputData("password"));

                System.out.println(cinema.getCurrentUser().IExist());
                System.exit(0);
            } catch (UserDetailsDoNotExistException | UserDetailsIncorrectException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void registerTester() {
        Scanner input = new Scanner(System.in);
        Cinema cinema = new Cinema();

        while (true) {
            try {
                System.out.println("Are you staff? ");

                if (input.nextLine().equals("y")) {
                    cinema.registerUser(inputData("username"), inputData("email"), inputData("password"), "staff", inputData("staffID"));

                } else {
                    cinema.registerUser(inputData("username"), inputData("email"), inputData("password"), "customer", null);
                }

                System.exit(0);
            } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void logoutTester() {
        Cinema cinema = new Cinema();

        cinema.deleteUser("LogoutTester1");
        cinema.deleteUser("LogoutTester2");

        try {
            cinema.registerUser("LogoutTester1", "logouttest1@tester.com", "pass1", "customer", null);
        } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException e) {
            System.out.println(e.getMessage() + "1");
        }

        try {
            cinema.registerUser("LogoutTester2", "logouttester2@tester.com", "pass2", "customer", null);
        } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException e) {
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

    private static String inputData(String data) {
        // Returns inputs as a string
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your " + data + ": ");

        return input.next();
    }

    private static void profileTester() {
        Cinema cinema = new Cinema();

        cinema.deleteUser("ProfileTester");
        cinema.deleteUser("NewTester");

        try {
            cinema.registerUser("ProfileTester", "test@tester.com", "pass", "customer", null);
        } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException e) {
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

    private static void bookingTester() {
        Cinema cinema = new Cinema();

        cinema.deleteUser("BookingTest");

        try {
            cinema.registerUser("BookingTest", "booking@booking.com", "bookingpass", "customer", null);
        } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException e) {
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
            cinema.bookFilm("2018-11-09", "12:00", filmTest, cinema.getScreen(1), "A", 5);
            cinema.bookFilm("2015-11-09", "12:00", filmTest2, cinema.getScreen(1), "B", 7);
        } catch (SeatAlreadyTakenException | SeatNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(cinema.getPastBookingsHistory());
        } catch (NoBookingsException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(cinema.getFutureBookingsHistory());
        } catch (NoBookingsException | NoFutureBookingsException e) {
            System.out.println(e.getMessage());
        }

        try {
            cinema.deleteFutureBooking(cinema.getBookingByTitle("FilmTest"));
        } catch (NoFutureBookingsException | NotAFutureBookingException | NoBookingsException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(cinema.getFutureBookingsHistory());
        } catch (NoBookingsException | NoFutureBookingsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void filmEditTester() {
        Cinema cinema = new Cinema();

        cinema.deleteFilm("UP");
        cinema.deleteFilm("UPTest");

        try {
            cinema.addFilm("UP", "Path", "A film about a man, a boy, and a dog", "01:00");
        } catch (FilmExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(cinema.getFilm("UP").getTitle());
        System.out.println(cinema.getFilm("UP").getImagePath());
        System.out.println(cinema.getFilm("UP").getRunTime());
        System.out.println(cinema.getFilm("UP").getDescription());

        try {
            cinema.setFilmTitle(cinema.getFilm("UP"), "UPTest");
            cinema.setFilmImagePath(cinema.getFilm("UPTest"), "PathTest");
            cinema.setFilmRunTime(cinema.getFilm("UPTest"), "02:00");
            cinema.setFilmDescription(cinema.getFilm("UPTest"), "DescriptionTest");
        } catch (FilmExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(cinema.getFilm("UPTest").getTitle());
        System.out.println(cinema.getFilm("UPTest").getImagePath());
        System.out.println(cinema.getFilm("UPTest").getRunTime());
        System.out.println(cinema.getFilm("UPTest").getDescription());
    }

    private static void seatsTester() {
        Cinema cinema = new Cinema();

        Screen screen = cinema.getScreen(1);

        System.out.println(screen.getSeats());

    }
}