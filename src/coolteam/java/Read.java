package coolteam.java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

class Read {

    static void readFile(String fileName) {

        // This will reference one line at a time
        String line;

        try {
            FileReader fileReader = new FileReader(fileName);
            // FileReader reads text files in the default encoding.

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println("Stored user information: ");
                System.out.println(line);
            }

            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");

        }
    }
}
