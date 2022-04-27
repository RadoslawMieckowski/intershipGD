package secondSet.countriesStatistics;

public class Country {

    private String name;
    private String continent;
    private long area;
    private long population;


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
