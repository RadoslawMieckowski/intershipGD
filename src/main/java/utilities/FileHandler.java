package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class FileHandler {

    private FileHandler(){}

    public static StringBuilder convertToOneString(String path) {
        File file = new File(path);
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            in.close();
            e.printStackTrace();
        }
        StringBuilder lineBuilder = new StringBuilder("");
        while (in.hasNextLine()) {
            lineBuilder.append(in.nextLine());
        }
        return lineBuilder;
    }

    public static String removeNonLetterAndMakeSmall(StringBuilder line) {
        return line.toString()
                .toLowerCase()
                .replaceAll("[^a-z]", "");
    }
}
