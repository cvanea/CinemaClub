package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import exceptions.IncorrectStaffIDException;
import exceptions.StaffIDTakenException;

import java.util.ArrayList;
import java.util.Scanner;

class Register {

    private DataBase dataBase;

    Register() {
        this.dataBase = DataBase.getInstance();
    }

    void registerUser() {
        //TODO Take user details and add them to the database, also assign them a unique userID.

        getDetails();

    }


    private ArrayList<String> getDetails() {

        ArrayList<String> userData = new ArrayList<>();

        if (isStaffRegister()) {
            // Staff

            String staffId = checkStaffID();

            userData.add("Staff");
            userData.add(inputData("username"));
            userData.add(inputData("email"));
            userData.add(inputData("password"));

            return userData;
        } else {
            // Customer
            userData.add("Customer");
            userData.add(inputData("username"));
            userData.add(inputData("email"));
            userData.add(inputData("password"));

            return userData;
        }
    }

    private String inputData(String data) {
        // Returns inputs as a string

        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your " + data + ": ");

        return input.next();
    }

    private Boolean isStaffRegister() {
        // Checks entered staffID
        Scanner input = new Scanner(System.in);

        System.out.print("Are you registering as staff?");

        return input.nextLine().equals("y");
    }

    private String checkStaffID() {
        Scanner input = new Scanner(System.in);

        while (true) {
            try {

                System.out.print("Please enter your assigned staffID: ");
                String staffId = input.next();

                checkID(staffId);

//                dataBase.useStaffID(staffId);

                return staffId;

            } catch (IncorrectStaffIDException | StaffIDTakenException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void checkID(String staffId) throws IncorrectStaffIDException, StaffIDTakenException {
        // Checks whether the staffID is correct
        if (dataBase.getStaffIDValue(staffId)) {
            throw new StaffIDTakenException();
        } else if (dataBase.getStaffIDValue(staffId) == null) {
            throw new IncorrectStaffIDException();
        }
    }

}
