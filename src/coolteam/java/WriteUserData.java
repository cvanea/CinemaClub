<<<<<<< HEAD:src/Main.java
//import java.io.FileWriter;
=======
package coolteam.java;

import java.io.FileWriter;
>>>>>>> master:src/coolteam/java/WriteUserData.java
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner;

class WriteUserData {

    static void writeUserData() throws IOException {

<<<<<<< HEAD:src/Main.java
        String textFile = "output.txt";
        Write.writeFile(textFile);
        Read.readFile(textFile);

=======
        ArrayList<String[]> userData = new ArrayList<>();

        String addUser;

        do {
            Scanner input = new Scanner(System.in);

            System.out.print("Please enter your name: ");
            String name = input.next();

            System.out.print("Please enter your email: ");
            String email = input.next();

            System.out.print("Please enter your password: ");
            String password = input.next();

            userData.add(new String[]{name, email, password});

            System.out.println("Do you wish to add another user? (y/n)");
            addUser = input.next();

        } while (addUser.equals("y"));

        final FileWriter writer = new FileWriter("output.txt");

        System.out.println("User information:");

        for (String[] i : userData) {
            System.out.println(Arrays.toString(i));
            writer.write(Arrays.toString(i));
        }

        writer.close();
>>>>>>> master:src/coolteam/java/WriteUserData.java
    }

}
