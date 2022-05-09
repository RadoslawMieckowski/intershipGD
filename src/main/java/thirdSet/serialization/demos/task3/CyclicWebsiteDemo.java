package thirdSet.serialization.demos.task3;

import thirdSet.serialization.models.UserExternalizable;
import thirdSet.serialization.models.Website;
import thirdSet.serialization.serializer.Serializer;

import java.util.List;

public class CyclicWebsiteDemo {
    public static void main(String[] args) {
        String path = "src/main/resources/data/serializationTargetCyclic.ser";
        Website facebook = new Website("Facebook");

        UserExternalizable user = new UserExternalizable(
                true, false, false,
                true, true, false
        );
        user.setWebsite(facebook);

        UserExternalizable user2 = new UserExternalizable(
                false, true, true,
                false, true, false
        );
        user2.setWebsite(facebook);
        facebook.setUserExternalizableList(List.of(user, user2));
        /*
        System.out.println("Before serialization: \n" + facebook + "\n");
        Serializer.serialize(facebook, path);
        Website facebookDeserialized = Serializer.deserialize(path);
        System.out.println("After deserialization: \n" + facebookDeserialized + "\n");

        System.out.println("Is serialized object equal to deserialized one? " +
                facebook.equals(facebookDeserialized));*/
        Serializer.serialize(user, path);
        UserExternalizable userDeserialized = Serializer.deserialize(path);
        System.out.println(user);
        System.out.println(userDeserialized);
        System.out.println(user.equals(userDeserialized));
    }
}
