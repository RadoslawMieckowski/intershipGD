package secondSet.countriesStatistics;

import secondSet.utilities.CSVReader;

import java.util.Arrays;
import java.util.List;

public class CountryDemo {
    public static void main(String[] args) {
        List<String> records = CSVReader.readCSVFile("src/main/resources/data/countriesStatistics.csv");
        //System.out.println("records: " + records);
        List<String[]> listOfTables = CSVReader.ToListOfTables(records);
        for (String[] table : listOfTables) {
            System.out.println(Arrays.toString(table));
        }
    }
}
