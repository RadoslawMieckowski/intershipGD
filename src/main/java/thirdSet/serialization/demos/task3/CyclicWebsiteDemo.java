package thirdSet.serialization.demos.task3;

import thirdSet.serialization.models.UserExternalizable;
import thirdSet.serialization.models.Website;
import thirdSet.serialization.serializer.Serializer;

import java.util.List;

public class CyclicWebsiteDemo {
    public static void main(String[] args) {
        final String path = "src/main/resources/data/serializationTargetCyclic.ser";
        Website facebook = new Website("Facebook");

        UserExternalizable user =
                UserExternalizable.builder()
                    .isActive(true)
                    .isAdmin(false)
                    .isModerator(false)
                    .isVIP(true)
                    .isMuted(true)
                    .isBanned(false)
                    .build();

        user.setWebsite(facebook);

        UserExternalizable user2 =
                UserExternalizable.builder()
                        .isActive(false)
                        .isAdmin(true)
                        .isModerator(true)
                        .isVIP(false)
                        .isMuted(true)
                        .isBanned(false)
                        .build();

        user2.setWebsite(facebook);
        facebook.setUserExternalizableList(List.of(user, user2));
        Serializer.serialize(user, path);
        UserExternalizable userDeserialized = Serializer.deserialize(path);
        System.out.println(user);
        System.out.println(userDeserialized);
        System.out.println(user.equals(userDeserialized));
    }
}
