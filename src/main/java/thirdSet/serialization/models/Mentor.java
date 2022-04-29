package thirdSet.serialization.models;

import java.io.Serializable;
import java.util.List;

public class Mentor implements Serializable {
    private static final long serialVersionUID = 7819523664148063820L;
    private final String name;
    private final String password;
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
}
