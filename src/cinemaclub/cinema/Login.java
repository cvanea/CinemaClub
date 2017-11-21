package cinemaclub.cinema;

import java.util.ArrayList;

public class Login {

    public static void loginUser() {

        getLoginDetails();

        if (correctDetails()) {
            // Instantiate customer or staff.
        } else {
            // Return error and try again.
        }
    }

    public static void getLoginDetails() {

        if (isStaff()) {
            // Staff
            getUsername();
            getEmail();
            getPassword();
        } else {
            // Customer
            getUsername();
            getEmail();
            getPassword();
        }
    }

    public static void getUsername() {

    }

    public static void getEmail() {

    }

    public static void getPassword() {

    }

    public static Boolean isStaff() {
        //

        return false;
    }

    public static Boolean correctDetails() {
        // Checks against hashmap for existing/matching user details.

        return false;
    }

}
