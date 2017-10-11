package coolteam.java;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Write {

    static void writeFile(String fileName, ArrayList<String[]> userData) throws IOException {
        FileWriter writer = new FileWriter(fileName);

        System.out.println("User information:");

        for (String[] i : userData) {
//            System.out.println(Arrays.toString(i));
            writer.write(Arrays.toString(i));
        }

        writer.close();
    }
}
