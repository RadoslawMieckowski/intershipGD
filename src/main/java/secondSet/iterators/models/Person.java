package secondSet.iterators.models;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
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


