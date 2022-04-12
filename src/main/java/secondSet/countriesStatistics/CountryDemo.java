package secondSet.countriesStatistics;

import secondSet.utilities.CSVReader;

import java.util.List;

public class CountryDemo {
    public static void main(String[] args) {
        List<String> records = CSVReader.readCSVFile("src/main/resources/data/countriesStatistics.csv");
        List<String[]> listOfTables = CSVReader.ToListOfStringTables(records);
        List<Country> countryList = CountriesStatistics.ToListOfCountries(listOfTables);
//        Country.sortByPopulationDesc(countryList);
//        Country.findCountryWithMaxPopulation(countryList);
//        Country.findCountryWithMinPopulation(countryList);
        //Country.findCountryWithMaxArea(countryList);
        //Country.findCountryWithMinArea(countryList);
//        Country.findCountryWithMaxPopulationOnContinent(countryList, "Africa");
        //Country.sortByContinentAndArea(countryList);
       // Country.sumOfAreaOfContinents(countryList);
//        Country.deleteCountriesWithPopulationOverThreshold(countryList, 90_000_000);
//        Country.listToMap(countryList);
    }
}
