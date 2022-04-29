package thirdSet.serialization.demo;

import thirdSet.serialization.models.Intern;
import thirdSet.serialization.models.Mentor;

import java.util.List;

public class SerializerDemo {
    public static void main(String[] args) {
        Mentor mentor = new Mentor("John" ,"MentorPassWord");
        Intern intern1 = new Intern("Mike", "MikePassword", mentor);
        Intern intern2 = new Intern("Tom", "TomPassword", mentor);
        Intern intern3 = new Intern("James", "JamesPassword", mentor);
        mentor.setInterns(List.of(intern1, intern2, intern3));
    }
}
