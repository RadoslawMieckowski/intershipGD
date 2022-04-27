package secondSet.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public final class CSVReader {
    private CSVReader(){}

    public static List<String> readCSVFile(String filePath) {
        List<String> entries = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            entries = reader.lines()
                    .skip(1)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public static List<String[]> ToListOfStringTables(List<String> records) {
        List<String[]> listOfTables = new LinkedList<>();
        records.stream()
                .map(x -> x.split(","))
                .forEach(x ->listOfTables.add(x));
        return listOfTables;
    }
}
