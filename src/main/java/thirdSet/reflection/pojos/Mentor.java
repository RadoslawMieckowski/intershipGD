package thirdSet.reflection.pojos;

import thirdSet.reflection.annotations.JsonAttribute;
import thirdSet.reflection.annotations.JsonSerializable;

import java.util.List;
@JsonSerializable
public class Mentor {
    @JsonAttribute(jsonFieldName = "list Of interns")
    private List<Intern> internList;

    public Mentor(List<Intern> internList) {
        this.internList = internList;
    }

    public Mentor() {
    }

    public List<Intern> getInternList() {
        return internList;
    }

    public void setInternList(List<Intern> internList) {
        this.internList = internList;
    }
}
