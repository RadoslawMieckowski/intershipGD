package thirdSet.reflection.pojos;

import thirdSet.reflection.JsonSerializer;
import thirdSet.reflection.annotations.JsonAttribute;
import thirdSet.reflection.annotations.JsonSerializable;

import java.util.Set;

@JsonSerializable
public class Intern {
    @JsonAttribute(jsonFieldName = "internName")
    private String name;
    @JsonAttribute
    private String surName;
    @JsonAttribute(jsonFieldName = "internAge")
    private int age;
    @JsonAttribute
    private Set<String> hobbies;

    public Intern(String name, String surName, int age, Set<String> hobbies) {
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.hobbies = hobbies;
    }

    public Intern() {
    }

    public void setHobbies(Set<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Set<String> getHobbies() {
        return hobbies;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @Override
    public String toString() {
        JsonSerializer jsonSerializer = new JsonSerializer();
        return jsonSerializer.serializePojoObject(this);
    }
}

