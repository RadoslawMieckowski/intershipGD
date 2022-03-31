package secondSet.countriesStatistics;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
        List<Country> countries = new LinkedList<>();
        for (String[] table : listOfTables) {
            countries.add(new Country(table[0], table[1], Long.valueOf(table[2]), Long.valueOf(table[3])));
        }
        return countries;
    }

    public static void sortByPopulationDesc(List<Country> countryList) {
        countryList.sort(populationComparator.reversed());
        Iterator iterator = countryList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void findCountryWithMaxPopulation(List<Country> countryList) {
        System.out.println(countryList.stream()
                .max(populationComparator)
                .orElseThrow());
    }

    public static void findCountryWithMinPopulation(List<Country> countryList) {
        System.out.println(countryList.stream()
                .min(populationComparator)
                .orElseThrow());
    }

    public static void findCountryWithMaxArea(List<Country> countryList) {
        System.out.println(countryList.stream()
                .max(areaComparator)
                .orElseThrow());
    }

    public static void findCountryWithMinArea(List<Country> countryList) {
        System.out.println(countryList.stream()
                .min(areaComparator)
                .orElseThrow());
    }

    public static void sortByContinentAndArea(List<Country> countryList) {
        countryList.stream()
                .sorted(areaComparator.reversed())
                .sorted(continentComparator)
                .forEach(e -> System.out.println(e));
    }

    public static void findCountryWithMaxPopulationOnContinent(List<Country> countryList, String continent) {
        System.out.println(countryList.stream()
                .filter(x -> x.continent.equals(continent))
                .max(populationComparator)
                .orElseThrow());
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
