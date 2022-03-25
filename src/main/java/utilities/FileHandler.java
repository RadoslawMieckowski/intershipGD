package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class FileHandler {

    private FileHandler(){}

    public static String convertToOneString(String path) {
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
            lineBuilder.append(in.nextLine().toLowerCase());
        }

        String stringToReturn = lineBuilder.toString()
                .replaceAll("[^a-z]", "");

        return stringToReturn;
    }
}
