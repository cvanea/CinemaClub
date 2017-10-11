package coolteam.java;

import java.util.ArrayList;
import java.util.Scanner;

class Register {

    static ArrayList<String[]> registerUser() {

        ArrayList<String[]> userData = new ArrayList<>();

        String addUser;
        String userType;

        do {
            Scanner input = new Scanner(System.in);

            if (isStaff()) {
                userType = "staff";
            } else userType = "employee";

            String name = regName();

            String email = regEmail();

            String password = regPassword();

            userData.add(new String[]{userType, name, email, password});

            System.out.println("Do you wish to add another user? (y/n)");
            addUser = input.next();

        } while (addUser.equals("y"));

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
                System.out.println("That's an incorrect ID");
                return false;
            }
        } else return false;
    }

    private static String regPassword() {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your password: ");
        return input.next();
    }

    private static String regName() {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your name: ");
        return input.next();
    }

    private static String regEmail() {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your email: ");
        return input.next();
    }

}
