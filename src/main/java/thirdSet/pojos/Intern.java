package thirdSet.pojos;

import thirdSet.annotations.JsonAttribute;
import thirdSet.annotations.JsonSerializable;

@JsonSerializable
public class Intern {
    @JsonAttribute(jsonFieldName = "internName")
    private String name;
    @JsonAttribute
    private String surName;

    public Intern(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    public Intern() {
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
}
