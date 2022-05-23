package thirdSet.serialization.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class Intern implements Serializable {
    private static final long serialVersionUID = 865711341535420413L;
    private final String name;
    private final transient String password;
    private final Mentor mentor;

    @Override
    public String toString() {
        return "Intern{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", mentor=" + (mentor != null ? mentor.getName() : " null") +
                '}';
    }
}
