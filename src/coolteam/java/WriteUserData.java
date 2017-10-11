package coolteam.java;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class WriteUserData {

    static void writeUserData(ArrayList<String[]> userData) throws IOException {

        final FileWriter writer = new FileWriter("output.txt");

        System.out.println("User information:");

        for (String[] i : userData) {
            writer.write(Arrays.toString(i));
        }

        writer.close();
    }

}
