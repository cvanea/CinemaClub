package coolteam.java;

import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "output.txt";
        Write.writeFile(fileName);
        Read.readFile(fileName);
    }
}