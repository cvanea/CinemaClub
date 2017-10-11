import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String textFile = "output.txt";

        Write writer = new Write();
        writer.writeFile(textFile);

        Read reader = new Read();
        reader.readFile(textFile);

    }
}