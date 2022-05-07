package thirdSet.serialization.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

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

    public List<UserExternalizable> getUserExternalizableList() {
        return userExternalizableList;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Website{" +
                "name='" + name + '\'' +
                ", userExternalizableList=" +
                        (userExternalizableList != null ?
                                Arrays.toString(userExternalizableList.
                                        toArray()) : " null") +
                '}';
    }
}
