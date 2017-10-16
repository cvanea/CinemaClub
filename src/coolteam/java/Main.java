package coolteam.java;

import java.io.IOException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws IOException {

        WriteUserData.writeUserData(Register.registerUser());

        Read.readFile("output.txt");

//        Scanner input = new Scanner(System.in);
//
//        Customer c1 = new Customer("Bob", "abc");
//        Staff s1 = new Staff("Sally", "bca");


    }
}