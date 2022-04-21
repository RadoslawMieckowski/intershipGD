package thirdSet.reflection;

import thirdSet.reflection.pojos.Intern;

import java.util.Set;

public class JsonSerializerDemo {
    public static void main(String[] args) {
        Intern intern = new Intern("John", "Smith",
                23, Set.of("music", "film"));
        JsonSerializer jsonSerializer = new JsonSerializer();
        String jsonString = jsonSerializer.serializePojoObject(intern);
        System.out.println(jsonString);
    }
}
