package thirdSet;

import thirdSet.pojos.Intern;

public class JsonSerializerDemo {
    public static void main(String[] args) {
        Intern intern = new Intern("John", "Smith");
        JsonSerializer jsonSerializer = new JsonSerializer();
        String jsonString = jsonSerializer.serializePojoObject(intern);
        System.out.println(jsonString);
    }
}
