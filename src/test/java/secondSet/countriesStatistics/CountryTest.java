package secondSet.countriesStatistics;

import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import secondSet.utilities.CSVReader;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CountryTest {
List<Country> countryList;

    @BeforeEach
    void setUp() {
        List<String> records = CSVReader.readCSVFile(
                "src/main/resources/data/countriesStatistics.csv");
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
    @DisplayName("Find country with max population")
    void findCountryWithMaxPopulation() {
        Country expectedCountry = new Country("China", "Asia",
                9_596_961, 1_412_600_000);
        Country actualCountry = Country.findCountryWithMaxPopulation(countryList);

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
    @DisplayName("Find country with min population")
    void findCountryWithMinPopulation() {
        Country expectedCountry = new Country("Austria", "Europe",
                83_879, 8_935_112);
        Country actualCountry = Country.findCountryWithMinPopulation(countryList);

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
    @DisplayName("Find country with max area")
    void findCountryWithMaxArea() {
        Country expectedCountry = new Country("Canada", "NorthAmerica",
                9_984_670, 38_526_760);
        Country actualCountry = Country.findCountryWithMaxArea(countryList);

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
    @DisplayName("Find country with min area")
    void findCountryWithMinArea() {
        Country expectedCountry = new Country("Austria", "Europe",
                83_879, 8_935_112);
        Country actualCountry = Country.findCountryWithMinArea(countryList);

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
    void sortByContinentAndArea() {
        Country expectedCountry = new Country("Algeria", "Africa",
                2_381_741, 44_700_000);
        Country.sortByContinentAndArea(countryList);
        Country actualCountry = countryList.get(0);

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
    @DisplayName("finding country with max population on continent")
    void findCountryWithMaxPopulationOnContinent() {
        Country expectedCountry = new Country("Egypt", "Africa",
                1_010_408, 102_674_145);
        Country actualCountry = Country.findCountryWithMaxPopulationOnContinent(
                countryList,"Africa");

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
    @DisplayName("Summing area of each continent basing on given countries")
    void sumOfAreaOfContinents() {
        Map<String, Long> expectedMap = new HashMap<>();
        expectedMap.put("Asia", 13_262_199L);
        expectedMap.put("Europe", 753_597L);
        expectedMap.put("Africa", 3_979_190L);
        expectedMap.put("SouthAmerica", 12_437_915L);
        expectedMap.put("NorthAmerica", 19_818_190L);

        Map<String, Long> actualMap = Country.sumOfAreaOfContinents(countryList);

       assertThat(actualMap, is(expectedMap));
       assertThat(actualMap.size(), is(5));
       assertThat(actualMap, IsMapContaining.hasEntry("Africa", 3_979_190L));
       assertThat(actualMap, IsMapContaining.hasKey("Europe"));
       assertThat(actualMap, IsMapContaining.hasValue(753_597L));
    }

    @Test
    @DisplayName("Deleting countries with population over a threshold")
    void deleteCountriesWithPopulationOverThreshold() {
        List<Country> actualCountryList = Country.deleteCountriesWithPopulationOverThreshold(
                countryList, 90_000_000
        );
        int actualCountryListSize = actualCountryList.size();

        assertEquals(8, actualCountryListSize);
    }

    @Test
    void listToMap() {
        Map<Character, List<String>> actualCountryMap = Country.listToMap(countryList);
        int actualCountryMapSize = actualCountryMap.size();

        assertEquals(10, actualCountryMapSize);
    }
}