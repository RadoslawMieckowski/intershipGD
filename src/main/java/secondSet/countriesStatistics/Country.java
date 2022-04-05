package secondSet.countriesStatistics;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Country {

    private String name;
    private String continent;
    private long area;
    private long population;
    private static Comparator<Country> populationComparator = Comparator.comparing(Country::getPopulation);
    private static Comparator<Country> areaComparator = Comparator.comparing(Country::getArea);
    private static Comparator<Country> continentComparator = Comparator.comparing(Country::getContinent);

    public long getPopulation() {
        return population;
    }

    public long getArea() {
        return area;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public Country(String name, String continent, long area, long population) {
        this.name = name;
        this.continent = continent;
        this.area = area;
        this.population = population;
    }
    public static List<Country> ToListOfCountries(List<String[]> listOfTables) {
        List<Country> countries =
            listOfTables.stream()
                    .map(table ->
                            (new Country(table[0], table[1], Long.valueOf(table[2]), Long.valueOf(table[3]))))
                    .collect(Collectors.toList());
        return countries;
    }

    public static void sortByPopulationDesc(List<Country> countryList) {
        countryList.sort(populationComparator.reversed());
        Iterator iterator = countryList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static Country findCountryWithMaxPopulation(List<Country> countryList) {
        Country country = countryList.stream()
                .max(populationComparator)
                .orElseThrow();
        System.out.println(country);
        return country;
    }

    public static Country findCountryWithMinPopulation(List<Country> countryList) {
        Country country = countryList.stream()
                .min(populationComparator)
                .orElseThrow();
        System.out.println(country);
        return country;
    }

    public static Country findCountryWithMaxArea(List<Country> countryList) {
        Country country = countryList.stream()
                .max(areaComparator)
                .orElseThrow();
        System.out.println(country);
        return country;
    }

    public static Country findCountryWithMinArea(List<Country> countryList) {
        Country country = countryList.stream()
                .min(areaComparator)
                .orElseThrow();
        System.out.println(country);
        return country;
    }

    public static void sortByContinentAndArea(List<Country> countryList) {
        countryList.sort(areaComparator.reversed());
        countryList.sort(continentComparator);
        countryList.forEach(e -> System.out.println(e));
    }

    public static Country findCountryWithMaxPopulationOnContinent(List<Country> countryList, String continent) {
        Country country = countryList.stream()
                .filter(x -> x.continent.equals(continent))
                .max(populationComparator)
                .orElseThrow();
        System.out.println(country);
        return country;
    }

    public static void sumOfAreaOfContinent(List<Country> countryList, String continent) {
        AtomicLong sum = new AtomicLong();
        countryList.stream()
                .filter(x -> x.continent.equals(continent))
                .forEach(x -> sum.addAndGet(x.area));
        System.out.println(sum);
    }

    public static List<Country> deleteCountriesWithPopulationOverThreshold(List<Country> countryList, long populationLimit) {
       List<Country> countries = countryList.stream()
                .filter(x -> x.population < populationLimit)
                .collect(Collectors.toList());

                countries.forEach(x -> System.out.println(x));
                return countries;
    }

    public static Map<Character, List<String>> listToMap(List<Country> countryList) {
        Map<Character, List<String>> countryMap = countryList.stream()
                .collect(Collectors.groupingBy(Country::getFirstLetterOfName,
                        Collectors.mapping(Country::toString,
                                Collectors.toList())));

                countryMap.forEach((key,value) -> System.out.println(key + " = " + value));
                return countryMap;
    }

    private static char getFirstLetterOfName(Country country) {
        return country.name.charAt(0);
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", area=" + area +
                ", population=" + population +
                '}';
    }
}
