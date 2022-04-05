package secondSet.countriesStatistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import secondSet.utilities.CSVReader;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class CountryTest {
List<Country> countryList;

    @BeforeEach
    void setUp() {
        List<String> records = CSVReader.readCSVFile("src/main/resources/data/countriesStatistics.csv");
        List<String[]> listOfTables = CSVReader.ToListOfStringTables(records);
        countryList = Country.ToListOfCountries(listOfTables);
    }

    @Test
    @DisplayName("Check sorting by population in descending order")
    void sortByPopulationDesc() {
        Country expectedCountry = new Country("Austria", "Europe",
                83_879, 8_935_112);
        Country.sortByPopulationDesc(countryList);
        Country actualCountry = countryList.get(countryList.size()-1);

        assertEquals(expectedCountry.getName(),actualCountry.getName(),
                "names should be the same!");
        assertEquals(expectedCountry.getContinent(),actualCountry.getContinent(),
                "continents should be the same!");
        assertEquals(expectedCountry.getArea(),actualCountry.getArea(),
                "areas should be the same!");
        assertEquals(expectedCountry.getPopulation(),actualCountry.getPopulation(),
                "population should be the same!");
    }

    @Test
    void findCountryWithMaxPopulation() {

    }

    @Test
    void findCountryWithMinPopulation() {
    }

    @Test
    void findCountryWithMaxArea() {
    }

    @Test
    void findCountryWithMinArea() {
    }

    @Test
    void sortByContinentAndArea() {
    }

    @Test
    void findCountryWithMaxPopulationOnContinent() {
    }

    @Test
    void sumOfAreaOfContinent() {
    }

    @Test
    void deleteCountriesWithPopulationOverThreshold() {
    }

    @Test
    void listToMap() {
    }
}