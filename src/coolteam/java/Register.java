package coolteam.java;

import java.util.ArrayList;
import java.util.Scanner;

class Register {

    static ArrayList<String[]> registerUser() {

        Scanner input = new Scanner(System.in);

        System.out.println("Do you wish to add a user? (y/n)");

        if (input.next().equals("y")) {
            if (isStaff()) {
                return register("staff");
            } else {
                return register("customer");
            }
        } else return null;
    }

    private static ArrayList<String[]> register(String userType) {

        ArrayList<String[]> userData = new ArrayList<>();

        String name = registerFields("name");

        String email = registerFields("email");

        String password = registerFields("password");

        userData.add(new String[]{userType, name, email, password});

        return userData;
    }

    private static Boolean isStaff() {
        Scanner input = new Scanner(System.in);

        System.out.print("Are you staff? (y/n) ");

        if (input.next().equals("y")) {
            System.out.println("What is the EmployeeID? ");
            if (input.nextInt() == 10) {
                return true;
            } else {
                System.out.println("That's an incorrect ID. Continuing registration as a customer.");
                return false;
            }
        } else return false;
    }

    private static String registerFields(String data) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your " + data + ": ");
        return input.next();
    }

}
