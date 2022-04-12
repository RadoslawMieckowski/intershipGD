package secondSet.countriesStatistics;

import secondSet.utilities.CSVReader;

import java.util.List;

public class CountriesStatisticsDemo {
    public static void main(String[] args) {
        List<String> records = CSVReader.readCSVFile("src/main/resources/data/countriesStatistics.csv");
        List<String[]> listOfTables = CSVReader.ToListOfStringTables(records);
        List<Country> countryList = CountriesStatistics.ToListOfCountries(listOfTables);
//        CountriesStatistics.sortByPopulationDesc(countryList);
//        CountriesStatistics.findCountryWithMaxPopulation(countryList);
//        CountriesStatistics.findCountryWithMinPopulation(countryList);
//        CountriesStatistics.findCountryWithMaxArea(countryList);
//        CountriesStatistics.findCountryWithMinArea(countryList);
//        CountriesStatistics.findCountryWithMaxPopulationOnContinent(countryList, "Africa");
//        CountriesStatistics.sortByContinentAndArea(countryList);
//        CountriesStatistics.sumOfAreaOfContinents(countryList);
//        CountriesStatistics.deleteCountriesWithPopulationOverThreshold(countryList, 90_000_000);
        CountriesStatistics.listToMap(countryList);
    }
}
