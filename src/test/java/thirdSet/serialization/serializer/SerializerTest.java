package thirdSet.serialization.serializer;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import thirdSet.serialization.models.*;

import java.io.IOException;
import java.util.List;

class SerializerTest {

    final String path = "src/main/resources/data/serializationTarget.ser";
    final String pathCyclic = "src/main/resources/data/serializationTargetCyclic.ser";
    final String pathUsers = "src/main/resources/data/serializationTarget3.ser";

    @Test
    void checkDeserializedInternsHaveSameMentor() throws IOException, ClassNotFoundException {
       Mentor mentor = new Mentor("John" ,"MentorPassWord");
       Intern intern1 = new Intern("Mike", "MikePassword", mentor);
       Intern intern2 = new Intern("Tom", "TomPassword", mentor);
       mentor.setInterns(List.of(intern1, intern2));

        Serializer.serialize(mentor, path);
        Mentor mentorDeserialized = Serializer.deserialize(path);
        Intern intern1Deserialized = mentorDeserialized.getInterns()
                .get(0);
        Intern intern2Deserialized = mentorDeserialized.getInterns()
                .get(1);

       assertThat(intern1Deserialized.getMentor())
                .isEqualTo(intern2Deserialized.getMentor());
    }

    @Test
    void serializeUserExternalizable() throws IOException, ClassNotFoundException {
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
        UserExternalizable userDeserialized = Serializer.deserialize(pathCyclic);

        assertThat(user).isEqualTo(userDeserialized);
    }

    @Test
    void serializeWebsite() throws IOException, ClassNotFoundException {
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
        Website facebookDeserialized = Serializer.deserialize(pathCyclic);

        assertThat(facebook).isEqualTo(facebookDeserialized);
    }

    @Test
    void serializeUser() throws IOException, ClassNotFoundException {
        User user = User.builder()
                .isActive(true)
                .isAdmin(false)
                .isModerator(false)
                .isVIP(true)
                .isMuted(true)
                .isBanned(false)
                .build();

        Serializer.serialize(user, pathUsers);
        User userDeserialized = Serializer.deserialize(pathUsers);

        assertThat(user.toString()).isEqualTo(userDeserialized.toString());
    }
}