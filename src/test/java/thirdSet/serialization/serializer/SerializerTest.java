package thirdSet.serialization.serializer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import thirdSet.serialization.models.*;

import java.io.IOException;
import java.util.List;

class SerializerTest {

    final String path = "src/main/resources/data/serializationTarget.ser";
    final String pathCyclic = "src/main/resources/data/serializationTargetCyclic.ser";
    final String pathUsers = "src/main/resources/data/serializationTarget3.ser";

    @Test
    void checkDeserializedInternsHaveSameMentor() {
       Mentor mentor = new Mentor("John" ,"MentorPassWord");
       Intern intern1 = new Intern("Mike", "MikePassword", mentor);
       Intern intern2 = new Intern("Tom", "TomPassword", mentor);
       mentor.setInterns(List.of(intern1, intern2));

        Serializer.serialize(mentor, path);
        Mentor mentorDeserialized = null;
        try {
            mentorDeserialized = Serializer.deserialize(path);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Intern intern1Deserialized = mentorDeserialized.getInterns()
                .get(0);
        Intern intern2Deserialized = mentorDeserialized.getInterns()
                .get(1);

        Assertions.assertThat(intern1Deserialized.getMentor())
                .isEqualTo(intern2Deserialized.getMentor());
    }

    @Test
    void serializeUserExternalizable() {
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

        Serializer.serialize(user, pathCyclic);
        UserExternalizable userDeserialized = null;
        try {
            userDeserialized = Serializer.deserialize(pathCyclic);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Assertions.assertThat(user).isEqualTo(userDeserialized);
    }

    @Test
    void serializeWebsite() {
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

        Serializer.serialize(facebook, pathCyclic);
        Website facebookDeserialized = null;
        try {
            facebookDeserialized = Serializer.deserialize(pathCyclic);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Assertions.assertThat(facebook).isEqualTo(facebookDeserialized);
    }

    @Test
    void serializeUser() {
        User user = User.builder()
                .isActive(true)
                .isAdmin(false)
                .isModerator(false)
                .isVIP(true)
                .isMuted(true)
                .isBanned(false)
                .build();

        Serializer.serialize(user, pathUsers);
        User userDeserialized = null;
        try {
           userDeserialized = Serializer.deserialize(pathUsers);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Assertions.assertThat(user.toString()).isEqualTo(userDeserialized.toString());
    }
}