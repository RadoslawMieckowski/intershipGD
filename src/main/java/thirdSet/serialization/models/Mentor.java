package thirdSet.serialization.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class Mentor implements Serializable {
    private static final long serialVersionUID = 7819523664148063820L;
    private final String name;
    private final transient String password;
    private List<Intern> interns;

    public Mentor(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setInterns(List<Intern> interns) {
        this.interns = interns;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", interns=" +
                    (interns == null ? null : interns.stream().map(Intern::getName)
                            .collect(Collectors.joining(", ", "{", "}"))) +
                '}';
    }
}
