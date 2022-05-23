package thirdSet.serialization.models;

import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class Website implements Serializable {
private final String name;
private List<UserExternalizable> userExternalizableList;
private static final long serialVersionUID = 8372080265705624161L;

    public Website(String name) {
        this.name = name;
    }

    public void setUserExternalizableList(List<UserExternalizable> userExternalizableList) {
        this.userExternalizableList = userExternalizableList;
    }

    @Override
    public String toString() {
        return "Website{" +
                "name='" + name + '\'' +
                ", userExternalizableList=" +
                        (userExternalizableList == null ? "null" : userExternalizableList.stream()
                                .map(UserExternalizable::toString)
                                .collect(Collectors.joining(", ", "{", "}"))) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Website website = (Website) o;
        return Objects.equals(name, website.name) && Objects.equals(userExternalizableList, website.userExternalizableList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userExternalizableList);
    }
}
