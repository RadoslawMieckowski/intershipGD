package secondSet.iterators;

public class Person <String, Integer>{
    private String name;
    private Integer age;


    public <E extends String, T extends Integer> Person(E name, T age) {
        this.name = name;
        this.age = age;
    }
}


