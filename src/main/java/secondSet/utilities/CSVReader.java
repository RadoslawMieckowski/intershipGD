package secondSet.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private static List<String> entries;
    public static void main(String[] args) {
        readCSVFile("src/main/resources/data/countriesStatistics.csv");
    }

    public static List<String> readCSVFile2(String filePath) {
        List<String> entries = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null) {
                entries.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("entries: " + entries);
        return entries;
    }

    public static List<String> readCSVFile(String filePath) {
        try {
            entries = Files.lines(Paths.get(filePath))
                    .skip(1)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(entries);
        return entries;
    }
}
