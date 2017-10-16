package coolteam.java;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.lang.NullPointerException;
import java.util.ArrayList;
import java.util.Arrays;

class WriteUserData {

    static void writeUserData(ArrayList<String[]> userData) throws IOException, NullPointerException {

        try {
            FileWriter writer = new FileWriter("output.txt", true);

            System.out.println("User information:");

            for (String[] i : userData) {
                writer.write(Arrays.toString(i));
            }

            writer.close();

        } catch (NoSuchFileException e) {
            FileWriter writer = new FileWriter("output.txt");

            System.out.println("User information:");

            for (String[] i : userData) {
                writer.write(Arrays.toString(i));
            }

            writer.close();
        }


    }

}
