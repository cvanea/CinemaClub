package cinemaclub.cinema;

import cinemaclub.user.User;
import exceptions.IncorrectStaffIDException;
import exceptions.StaffIDTakenException;
import exceptions.UserDetailsDoNotExistException;
import exceptions.UsernameTakenException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        registerTester();
//        loginTester();

    }

    private static void loginTester() {
        Scanner input = new Scanner(System.in);
        Cinema cinema = new Cinema();

        User user;

        while (true) {
            try {
                System.out.println("Are you staff? ");

                if (input.nextLine().equals("y")) {
                    user = cinema.loginUser(inputData("username"), inputData("email"), inputData("password"), "staff");

                } else {
                    user = cinema.loginUser(inputData("username"), inputData("email"), inputData("password"), "customer");
                }

                System.out.println(user.IExist());
                System.out.println(user.getName() + "'s login status is: " + user.getLoggedOn());
                System.exit(0);
            } catch (UserDetailsDoNotExistException e) {
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
}