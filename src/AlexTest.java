import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AlexTest {
    public static void main(String[] args) {

        ArrayList<String[]> userData = new ArrayList<>();

        for (int n = 0; n < 2; n++) {
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter your name: ");
            String name = input.next();
            System.out.print("Please enter your email: ");
            String email = input.next();
            System.out.print("Please enter your password: ");
            String password = input.next();
            userData.add(new String[]{name, email, password});
            System.out.println(Arrays.toString(userData.get(n)));
        }


//        while (true) {
//            System.out.println("Please enter your password: ");
//            password.add(sc.next());
//        }


        /* */
//        String[][] user = new String[2][2];
//        List<String[]> userData = new ArrayList<>();
//        Scanner input = new Scanner(System.in);
//        System.out.println("Your name?:");
//        userData[][]= input.nextLine();
////        user[0][0] = input.nextLine();
//        System.out.println("Your Password?:");
////        user[0][1] = input.nextLine();
//        System.out.println();



    }
}
