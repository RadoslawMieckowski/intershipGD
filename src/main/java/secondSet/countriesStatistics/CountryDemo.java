package secondSet.countriesStatistics;

import secondSet.utilities.CSVReader;

public class CountryDemo {
    public static void main(String[] args) {
        CSVReader.readCSVFile("src/main/resources/data/countriesStatistics.csv");
    }
}
