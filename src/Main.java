import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<String[]> userData = new ArrayList<>();

        String adduser;

        do {
            Scanner input = new Scanner(System.in);

            System.out.print("Please enter your name: ");
            String name = input.next();

            System.out.print("Please enter your email: ");
            String email = input.next();

            System.out.print("Please enter your password: ");
            String password = input.next();

            userData.add(new String[]{name, email, password});

            System.out.println("Do you wish to add another user?");
            adduser = input.next();

        } while (adduser.equals("y"));

        for (String[] i : userData) {
            System.out.println(Arrays.toString(i));
        }
    }
}