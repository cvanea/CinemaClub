package coolteam.java;

import java.io.IOException;

class Main {
    static void main(String[] args) throws IOException {

        WriteUserData.writeUserData();

        Read.readFile("output.txt");

    }
}