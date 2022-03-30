package secondSet.countriesStatistics;

import secondSet.utilities.CSVReader;

import java.util.List;

public class CountryDemo {
    public static void main(String[] args) {
        List<String> records = CSVReader.readCSVFile("src/main/resources/data/countriesStatistics.csv");
        List<String[]> listOfTables = CSVReader.ToListOfStringTables(records);
        List<Country> countryList = Country.ToListOfCountries(listOfTables);
        System.out.println(countryList);
    }
}
