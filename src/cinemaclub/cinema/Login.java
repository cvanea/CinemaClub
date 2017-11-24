package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.UserCredentials;
import cinemaclub.user.*;
import exceptions.UserDetailsDoNotExistException;

import java.util.Scanner;

class Login {

    private DataBase dataBase;

    Login() {
        this.dataBase = DataBase.getInstance();
    }

    User loginUser() {

        while (true) {
            try {
                UserCredentials userCredentials = getDetails();
                correctDetails(userCredentials);

                if (userCredentials.getUserType().equals("staff")) {
                    // Instantiate customer or staff.

                    return new Staff(userCredentials.getUserName(), userCredentials.getEmail(),
                        userCredentials.getPassword());
                } else {

                    return new Customer(userCredentials.getUserName(), userCredentials.getEmail(),
                        userCredentials.getPassword());
                }
            } catch (UserDetailsDoNotExistException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private UserCredentials getDetails() {

        if (isStaff()) {
            // Staff
            return new UserCredentials(inputData("username"), inputData("email"), inputData("password"), "staff");
        } else {
            // Customer
            return new UserCredentials(inputData("username"), inputData("email"), inputData("password"), "customer");
        }
    }

    private String inputData(String data) {
        // Returns inputs as a string

        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your " + data + ": ");

        return input.next();
    }

    private Boolean isStaff() {
        Scanner input = new Scanner(System.in);

        System.out.print("Are you staff?");

        return input.nextLine().equals("y");
    }

    private void correctDetails(UserCredentials userCredentials) throws UserDetailsDoNotExistException {
        UserCredentials savedCredentials = dataBase.getUserCredentials(userCredentials.getUserName());
        if (!userCredentials.checkCredentials(savedCredentials)) {
            throw new UserDetailsDoNotExistException();
        }
    }

}
