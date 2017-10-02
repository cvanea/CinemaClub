import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

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

        System.out.println("User information:");
        FileWriter writer = new FileWriter("output.txt");
        for (String[] i : userData) {
            System.out.println(Arrays.toString(i));
            writer.write(Arrays.toString(i));
        }


//        for(String str: arr) {

        writer.close();

    }
}