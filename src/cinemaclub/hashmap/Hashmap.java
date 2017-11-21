package cinemaclub.hashmap;

import java.util.ArrayList;

public class Hashmap {

    public static ArrayList<String> arrayListUserID() {
        ArrayList<String> userID = new ArrayList<>();

        userID.add("1");
        userID.add("2");
        userID.add("3");

        return userID;
    }

    public static ArrayList<ArrayList<String>> arrayListUserDetails() {
        ArrayList<ArrayList<String>> userID = new ArrayList<>();

        ArrayList<String> user1 = new ArrayList<>();
        ArrayList<String> user2 = new ArrayList<>();

        user1.add("Staff");
        user1.add("Claudia");
        user1.add("claudia.vanea@hotmail.co.uk");
        user1.add("pass");

        userID.add(user1);

        user2.add("Customer");
        user2.add("Bob");
        user2.add("bob@hotmail.co.uk");
        user2.add("pass2");

        userID.add(user2);

        return userID;
    }
}
