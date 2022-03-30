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

    public long getPopulation() {
        return population;
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

    public static void sortByPopulationDesc(List countryList) {
        Comparator<Country> populationComparator = Comparator.comparing(Country::getPopulation);
        countryList.sort(populationComparator.reversed());
        Iterator iterator = countryList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
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
