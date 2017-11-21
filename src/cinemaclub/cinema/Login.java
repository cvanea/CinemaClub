package cinemaclub.cinema;

import cinemaclub.hashmap.Hashmap;
import cinemaclub.user.*;
import exceptions.*;

import java.util.ArrayList;
import java.util.Scanner;

class Login {

    User loginUser() {

        while (true) {
            try {
                ArrayList<String> userDetails = getLoginDetails();
                correctDetails(userDetails);

                if (userDetails.get(0).equals("Staff")) {
                    // Instantiate customer or staff.

                    return new Staff(true, userDetails.get(1), userDetails.get(2), userDetails.get(3));

                } else {

                    return new Customer(true, userDetails.get(1), userDetails.get(2), userDetails.get(3));

                }

            } catch (UserDetailsDoNotExistException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private ArrayList<String> getLoginDetails() {

        ArrayList<String> userDetails = new ArrayList<>();

        if (isStaff()) {
            // Staff
            userDetails.add("Staff");
            userDetails.add(inputData("username"));
            userDetails.add(inputData("email"));
            userDetails.add(inputData("password"));

            return userDetails;
        } else {
            // Customer
            userDetails.add("Customer");
            userDetails.add(inputData("username"));
            userDetails.add(inputData("email"));
            userDetails.add(inputData("password"));

            return userDetails;
        }
    }

    private String inputData(String data) {
        // Returns username string

        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your " + data + ": ");

        return input.next();
    }

    private Boolean isStaff() {
        // Checks for entered staffID

        while (true) {
            try {
                Scanner input = new Scanner(System.in);

                System.out.print("Are you staff?");

                if (input.nextLine().equals("y")) {
                    String staffID = inputData("staffID");
                    isIDFree(staffID);
                    return true;
                } else {
                    return false;
                }
            } catch (IncorrectStaffIDException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void isIDFree(String staffID) throws IncorrectStaffIDException {
        // Checks whether the staffID is taken
        if (!Hashmap.arrayListUserID().contains(staffID)) {
            throw new IncorrectStaffIDException();
        }
    }

    private void correctDetails(ArrayList<String> userDetails) throws UserDetailsDoNotExistException {
        // Checks against hashmap for existing/matching user details.

        if (!Hashmap.arrayListUserDetails().contains(userDetails)) {
            throw new UserDetailsDoNotExistException();
        }
    }

}
