package secondSet.countriesStatistics;

import secondSet.utilities.CSVReader;

import java.util.List;

public class CountryDemo {
    public static void main(String[] args) {
        List<String> records = CSVReader.readCSVFile("src/main/resources/data/countriesStatistics.csv");
        List<String[]> listOfTables = CSVReader.ToListOfStringTables(records);
        List<Country> countryList = Country.ToListOfCountries(listOfTables);
        //Country.sortByPopulationDesc(countryList);
        //Country.findCountryWithMaxPopulation(countryList);
        //Country.findCountryWithMinPopulation(countryList);
        //Country.findCountryWithMaxArea(countryList);
        //Country.findCountryWithMinArea(countryList);
        //Country.findCountryWithMaxPopulationOnContinent(countryList, "Africa");
        Country.sortByContinentAndArea(countryList);
    }
}
