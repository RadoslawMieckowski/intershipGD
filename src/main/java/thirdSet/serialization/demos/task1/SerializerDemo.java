package thirdSet.serialization.demos.task1;

import thirdSet.serialization.models.Intern;
import thirdSet.serialization.models.Mentor;
import thirdSet.serialization.serializer.Serializer;

import java.io.IOException;
import java.util.List;

public class SerializerDemo {
    public static void main(String[] args) {
        Mentor mentor = new Mentor("John" ,"MentorPassWord");
        Intern intern1 = new Intern("Mike", "MikePassword", mentor);
        Intern intern2 = new Intern("Tom", "TomPassword", mentor);
        Intern intern3 = new Intern("James", "JamesPassword", mentor);
        mentor.setInterns(List.of(intern1, intern2, intern3));

        System.out.println("Before serialization:"+ "\n");
        System.out.println(mentor);
        System.out.println(intern1);
        System.out.println(intern2);
        System.out.println(intern3);
        System.out.println();

        final String path = "src/main/resources/data/serializationTarget.ser";
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
        Intern intern3Deserialized = mentorDeserialized.getInterns()
                .get(2);

        System.out.println("After serialization:" + "\n");
        System.out.println(mentorDeserialized);
        System.out.println(intern1Deserialized);
        System.out.println(intern2Deserialized);
        System.out.println(intern3Deserialized);

        System.out.printf("Hash code of intern1Deserialized's mentor: %d\n",
                intern1Deserialized.getMentor().hashCode());

        System.out.printf("Hash code of Intern2Deserialized's mentor: %d\n",
                intern2Deserialized.getMentor().hashCode());
        System.out.printf("Hash code of intern3Deserialized's mentor: %d\n",
                intern3Deserialized.getMentor().hashCode());
    }
}
