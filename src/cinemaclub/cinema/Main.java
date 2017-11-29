package cinemaclub.cinema;

import cinemaclub.user.Customer;
import cinemaclub.user.UserCredentials;
import exceptions.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        registerTester();
//        loginTester();
//        setProfileTester();
        profileTester();

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

    private static void getProfileTester() {
        Cinema cinema = new Cinema();
        cinema.setCurrentUser(new Customer(new UserCredentials("Tester", "test@tester.com", "pass")));

        System.out.println(cinema.getProfileDetails().getUserName());
        System.out.println(cinema.getProfileDetails().getEmail());
        System.out.println(cinema.getProfileDetails().getPassword());
    }

    private static void profileTester() {
        Cinema cinema = new Cinema();

//        try {
//            cinema.registerUser("ProfileTester", "test@tester.com", "pass", "customer", null);
//        } catch (UsernameTakenException | IncorrectStaffIDException | StaffIDTakenException e) {
//            System.out.println(e.getMessage());
//        }

        try {
            cinema.loginUser("ProfileTester", "test@tester.com", "pass");
        } catch (UserDetailsDoNotExistException | UserDetailsIncorrectException e) {
            System.out.println(e.getMessage());
        }

//        cinema.setCurrentUser(new Customer(new UserCredentials("Tester", "test@tester.com", "pass")));

        System.out.println(cinema.getProfileDetails().getUserName());
        System.out.println(cinema.getProfileDetails().getEmail());
        System.out.println(cinema.getProfileDetails().getPassword());

        cinema.getProfileDetails().setUserName("NewTester");
        cinema.getProfileDetails().setEmail("newtester@tester.com");
        cinema.getProfileDetails().setPassword("newpass");

        System.out.println(cinema.getProfileDetails().getUserName());
        System.out.println(cinema.getProfileDetails().getEmail());
        System.out.println(cinema.getProfileDetails().getPassword());

    }
}