package cinemaclub.cinema;

import exceptions.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        registerTester();
//        loginTester();
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

    private static void profileTester() {
        Cinema cinema = new Cinema();

        cinema.deleteUser("ProfileTester");
        cinema.deleteUser("NewTester");

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

        System.out.println(cinema.getProfileDetails().getUserName());
        System.out.println(cinema.getProfileDetails().getEmail());
        System.out.println(cinema.getProfileDetails().getPassword());

        try {
            cinema.setUsername("NewTester");
            cinema.setUserEmail("newtester@tester.com");
            cinema.setUserPassword("newpass");
        } catch (UsernameTakenException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(cinema.getProfileDetails().getUserName());
        System.out.println(cinema.getProfileDetails().getEmail());
        System.out.println(cinema.getProfileDetails().getPassword());

    }
}