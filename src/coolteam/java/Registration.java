package coolteam.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Registration {

    static ArrayList<String[]> registerUser()throws IOException {

        ArrayList<String[]> userData = new ArrayList<>();

        String addUser;
        do {

            String userType = staffCheck();
            String name = addName();
            String email = addEmail();
            String password = addPassword();

            userData.add(new String[]{name, email, password, userType});

            Scanner input = new Scanner(System.in);
            System.out.println("Do you wish to add another user? (y/n)");
            addUser = input.next();

        } while (addUser.equals("y"));
        return userData;
    }

    static String staffCheck(){
        String userType = "Employee";
        Scanner input = new Scanner(System.in);
        System.out.print("Staff? (y/n)");
        if(input.next().equals("y")){
            System.out.print("What is your employee ID?");
            if(10 == input.nextInt()){
                userType = "Staff";
                return userType;
            }else{
                System.out.println("Incorrect Employee ID... Ongoing Normal Registration");
                return userType;
            }
        }
        return userType;
    }
    static String addName(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name = input.next();
        return name;

    }
    static String addEmail(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your email: ");
        String email = input.next();
        return email;

    }

    static String addPassword(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your password: ");
        String password = input.next();
        return password;
    }



}
