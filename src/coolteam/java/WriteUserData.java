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

            for (String[] i : userData) {
                writer.write(Arrays.toString(i));
            }

            writer.close();

        } catch (NullPointerException e) {
            System.out.println("No user input");

        } catch (NoSuchFileException e) {
            FileWriter writer = new FileWriter("output.txt");

            for (String[] i : userData) {
                writer.write(Arrays.toString(i));
            }

            writer.close();
        }
    }
}
