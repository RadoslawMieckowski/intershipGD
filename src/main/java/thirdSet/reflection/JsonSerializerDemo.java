package thirdSet.reflection;

import thirdSet.reflection.pojos.Intern;
import thirdSet.reflection.pojos.Mentor;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class JsonSerializerDemo {
    public static void main(String[] args) {
        Intern intern1 = new Intern("John", "Smith",
                23, new LinkedHashSet<>(Set.of("music", "film")));
        Intern intern2 = new Intern("Tom", "Sparrow",
                26, new LinkedHashSet<>(Set.of("cars", "boats")));
        Intern intern3 = new Intern("Michael", "Doe",
                25, new LinkedHashSet<>(Set.of("hiking", "swimming")));
        JsonSerializer jsonSerializer = new JsonSerializer();
        String jsonString1 = jsonSerializer.serializePojoObject(intern1);

        System.out.println(jsonString1 + "\n");

        Mentor mentor = new Mentor("David", List.of(intern1, intern2, intern3));
        String jsonString2 = jsonSerializer.serializePojoObject(mentor);

        System.out.println(jsonString2);
    }
}
