package thirdSet.serialization.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Mentor implements Serializable {
    private static final long serialVersionUID = 7819523664148063820L;
    private final String name;
    private final transient String password;
    private List<Intern> interns;

    public Mentor(String name, String password, List<Intern> interns) {
        this.name = name;
        this.password = password;
        this.interns = interns;
    }

    public Mentor(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setInterns(List<Intern> interns) {
        this.interns = interns;
    }

    public List<Intern> getInterns() {
        return interns;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        List internsNames = new LinkedList();
        if(interns != null) {
            interns.forEach(intern -> internsNames.add(intern.getName()));
        }
        return "Mentor{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", interns=" +
                    (interns != null ? Arrays.toString(internsNames.toArray()) : " null") +
                '}';
    }
}
