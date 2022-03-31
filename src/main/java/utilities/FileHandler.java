package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class FileHandler {

    private FileHandler(){}

    public static StringBuilder convertToOneString(String path) {
        File file = new File(path);
        StringBuilder lineBuilder = new StringBuilder();
        try (Scanner in = new Scanner(file)){
            while (in.hasNextLine()) {
                lineBuilder.append(in.nextLine() + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lineBuilder;
    }

    public static String removeNonLetterAndMakeSmall(StringBuilder line) {
        return line.toString()
                .toLowerCase()
                .replaceAll("[^a-z]", "");
    }
}
