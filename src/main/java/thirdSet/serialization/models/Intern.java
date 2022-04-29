package thirdSet.serialization.models;

import java.io.Serializable;

public class Intern implements Serializable {
    private static final long serialVersionUID = 865711341535420413L;
    private final String name;
    private final String password;
    private final Mentor mentor;

    public Intern(String name, String password, Mentor mentor) {
        this.name = name;
        this.password = password;
        this.mentor = mentor;
    }

    @Override
    public String toString() {
        return "Intern{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", mentor=" + mentor +
                '}';
    }
}
