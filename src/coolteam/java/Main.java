package coolteam.java;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        WriteUserData.writeUserData();

        Read.readFile("output.txt");

    }
}