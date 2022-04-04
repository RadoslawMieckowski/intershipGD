package secondSet.iterators.models;

public class Person <String, Integer>{
    private String name;
    private Integer age;


    public <E extends String, T extends Integer> Person(E name, T age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public java.lang.String toString() {
        return "Person{" +
                "name=" + name +
                ", age=" + age +
                '}';
    }
}


