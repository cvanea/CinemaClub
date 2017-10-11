package coolteam.java;

import java.io.IOException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws IOException {

        WriteUserData.writeUserData(Register.registerUser());

        Read.readFile("output.txt");

        Scanner input = new Scanner(System.in);

        Customer c1 = new Customer("Bob", "abc");
        Staff s1 = new Staff("Sally", "bca");

//        System.out.println("You are trying to set password as staff. What is your Employee ID?");
//
//        int employeeIDInput = input.nextInt();
//
//        if (s1.employeeID == employeeIDInput) {
//            System.out.println("What is your password?");
//            String passwordInput = input.next();
//            s1.setPassword(passwordInput);
//        } else System.out.println("Incorrect employeeID.");
//
//        System.out.println(s1.getPassword());

    }
}