package thirdSet.serialization.demo;

import thirdSet.serialization.models.Intern;
import thirdSet.serialization.models.Mentor;
import thirdSet.serialization.serializer.Serializer;

import java.util.List;

public class SerializerDemo {
    public static void main(String[] args) {
        Mentor mentor = new Mentor("John" ,"MentorPassWord");
        Intern intern1 = new Intern("Mike", "MikePassword", mentor);
        Intern intern2 = new Intern("Tom", "TomPassword", mentor);
        Intern intern3 = new Intern("James", "JamesPassword", mentor);
        mentor.setInterns(List.of(intern1, intern2, intern3));

        Serializer.serialize(mentor, "src/main/resources/data/serializationTarget.txt");
        Mentor mentorDeserialized = Serializer.deserialize("src/main/resources/data/serializationTarget.txt");
        Intern retrievedIntern1 = mentorDeserialized
                .getInterns()
                .get(0);
        Intern retrievedIntern2 = mentorDeserialized
                .getInterns()
                .get(1);
        Intern retrievedIntern3 = mentorDeserialized
                .getInterns()
                .get(2);

        System.out.printf("Hash code of mentor: %d\t" +
                "Hash code of mentorDeserialized: %d\t" +
                " result of equals(): %b\n",
                mentor.hashCode(), mentorDeserialized.hashCode(), mentor.equals(mentorDeserialized)
        );
        System.out.printf("Hash code of intern1: %d\t" +
                        "Hash code of retrievedIntern1: %d\t" +
                        " result of equals(): %b\n",
                intern1.hashCode(), retrievedIntern1.hashCode(), intern1.equals(retrievedIntern1)
        );
        System.out.printf("Hash code of intern2: %d\t" +
                        " Hash code of retrievedIntern2: %d\t" +
                        " result of equals(): %b\n",
                intern2.hashCode(), retrievedIntern2.hashCode(), intern2.equals(retrievedIntern1)
        );
        System.out.printf("Hash code of intern3: %d\t" +
                        " Hash code of retrievedIntern3: %d\t" +
                        " result of equals(): %b\n",
                intern3.hashCode(), retrievedIntern3.hashCode(), intern3.equals(retrievedIntern1)
        );
    }
}
