package thirdSet.reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import thirdSet.reflection.pojos.Intern;

import java.util.LinkedHashSet;
import java.util.Set;

class JsonSerializerTest {
    JsonSerializer jsonSerializer;

    @BeforeEach
    void setUp() {
        jsonSerializer = new JsonSerializer();
    }

    @Test
    @DisplayName("serializePojoObject should return JSON string with order" +
            " according to declared fields in class")
    void serializePojoObjectTest() {
        Intern intern = new Intern("John", "Smith",
                23, new LinkedHashSet<>(Set.of("music", "film")));
        String actualJsonString = jsonSerializer.serializePojoObject(intern);

        String expectedJsonString = "{\"internName\":\"John\", \"surName\":\"Smith\"," +
                " \"internAge\":\"23\", \"hobbies\":\"[film, music]\"}";

        Assertions.assertEquals(expectedJsonString, actualJsonString,
                "serializePojoObject should return JSON string " +
                        "with order according to declared fields in class!");
    }

}