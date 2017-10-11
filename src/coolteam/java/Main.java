package coolteam.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "output.txt";
        ArrayList<String[]> userData = Registration.registerUser();
        Write.writeFile(fileName, userData);
        Read.readFile(fileName);

        Customer c1 = new Customer("Alex", "aep.charles@gmail.com");
        Staff s1 = new Staff("Claudia", "claudia@gmail.com", 20);
//        c1.setLoggedOn(true);
//        s1.setLoggedOn(false);

//       System.out.println(s1.getPassword());

    }
}