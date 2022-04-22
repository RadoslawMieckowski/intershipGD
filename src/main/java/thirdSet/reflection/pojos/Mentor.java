package thirdSet.reflection.pojos;

import thirdSet.dynamicProxy.interfaces.Printable;
import thirdSet.reflection.JsonSerializer;
import thirdSet.reflection.annotations.JsonAttribute;
import thirdSet.reflection.annotations.JsonSerializable;

import java.util.List;
@JsonSerializable
public class Mentor implements Printable {
    @JsonAttribute()
    private String name;
    @JsonAttribute(jsonFieldName = "list Of interns")
    private List<Intern> internList;

    public Mentor(String name, List<Intern> internList) {
        this.name = name;
        this.internList = internList;
    }

    public Mentor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Intern> getInternList() {
        return internList;
    }

    public void setInternList(List<Intern> internList) {
        this.internList = internList;
    }

    @Override
    public String toString() {
        JsonSerializer jsonSerializer = new JsonSerializer();
        return jsonSerializer.serializePojoObject(this);
    }
}
