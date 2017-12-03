package cinemaclub.cinema;

import cinemaclub.user.Booking;
import cinemaclub.user.Customer;
import cinemaclub.user.UserCredentials;
import exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainTester {

    public static void main(String[] args) {

//        registerTester();
//        loginTester();
//        profileTester();
        bookingHistoryTester();
//        filmEditTester();
//        seatsTester();

    }

    private static void loginTester() {
        Cinema cinema = new Cinema();

        while (true) {
            try {
                cinema.loginUser(inputData("username"), inputData("email"), inputData("password"));

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

//        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));

//        ArrayList<Booking> bookings = new ArrayList<>();
//
//        bookings.add(new Booking("UP", LocalDateTime.now()));
//        bookings.add(new Booking("IT", LocalDateTime.now()));
//
//        Customer customer = new Customer(new UserCredentials("Test", "test@test", "pass"), bookings);
//        System.out.println(customer);


        try {
            cinema.registerUser("ProfileTester", "test@tester.com", "pass", "customer", null);
        } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        try {
            cinema.loginUser("ProfileTester", "test@tester.com", "pass");
        } catch (UserDetailsDoNotExistException | UserDetailsIncorrectException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

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

    private static void bookingHistoryTester() {
        Cinema cinema = new Cinema();

        Customer customer = new Customer(new UserCredentials("BookingTest", "booking@booking.com", "bookingpass"), new ArrayList<>());

        customer.addBooking(new Booking("Test", "2017-11-09", "10:30", 1, "A1"));

        Booking test = customer.getBookingByTitle("Test");
        String bookingDateTime = test.getDate() + " " + test.getTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String timeNow = LocalDateTime.now().format(formatter);

        System.out.println(bookingDateTime);
        System.out.println(timeNow);
        System.out.println(bookingDateTime.compareTo(timeNow));


        try {
            System.out.println(cinema.getBookingsHistory(customer));
        } catch (NoBookingsException e) {
            e.getMessage();
        }

        try {
            System.out.println(cinema.getPastBookingsHistory(customer));
        } catch (NoBookingsException e) {
            e.getMessage();
        }
    }

    private static void filmEditTester() {
        Cinema cinema = new Cinema();

        cinema.deleteFilm("UP");
        cinema.deleteFilm("UPTest");

        try {
            cinema.addFilm("UP", "Path", 1, "A film about a man a boy and a dog");
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
            cinema.setFilmRunTime(cinema.getFilm("UPTest"), 2);
            cinema.setFilmDescription(cinema.getFilm("UPTest"), "DescriptionTest");
        } catch (FilmExistsException e) {
            e.getMessage();
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