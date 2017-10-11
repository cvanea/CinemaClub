package coolteam.java;

import java.io.IOException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "output.txt";
        Write.writeFile(fileName);
        Read.readFile(fileName);

        Customer c1 = new Customer("Alex", "aep.charles@gmail.com");
        Staff s1 = new Staff("Claudia", "claudia@gmail.com", 20);

        Scanner input = new Scanner(System.in);
        System.out.println("Staff? What is your employee ID");

        if(s1.employeeID == input.nextInt()){
            System.out.println("Input Password");
            String userPass = input.next();
            s1.setPassword(userPass);
        }else{
            System.out.println("Incorrect Employee ID");
        }
//        c1.setLoggedOn(true);
//        s1.setLoggedOn(false);

       System.out.println(s1.getPassword());

    }
}