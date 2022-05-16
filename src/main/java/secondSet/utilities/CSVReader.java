package secondSet.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static String prepareToInsertSQLValues(String filePath) {
        List<String> entries = null;
        String sqlValues = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            entries = reader.lines()
                    .skip(1)
                    .map(CSVReader::addChars)
                    .collect(Collectors.toList());
        }catch (IOException e) {
            e.printStackTrace();
        }
        return ListToSQLValues(entries);
    }

    private static String addChars(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        return stringBuilder.insert(0, '(')
                .toString()
                .replace(",", ",'")
                .concat("')");
    }

    private static String ListToSQLValues(List<String> list) {
        StringBuilder sqlValues = new StringBuilder();
        list.stream()
                .forEach(line -> sqlValues.append(line.concat(",\n")));
        return sqlValues.delete(sqlValues.length() - 2, sqlValues.length())
                .toString();
    }
}
