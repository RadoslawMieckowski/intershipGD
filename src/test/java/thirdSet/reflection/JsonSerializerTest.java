package thirdSet.reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import thirdSet.reflection.exceptions.JsonSerializationException;
import thirdSet.reflection.pojos.Intern;
import thirdSet.reflection.pojos.Student;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

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

    @Test
    @DisplayName("serializing null should throw JsonSerializationException")
    void serializeNullTest() {
        Intern intern = null;

        Throwable exception = assertThrows(JsonSerializationException.class, () ->
                jsonSerializer.serializePojoObject(intern));

        assertTrue(exception instanceof JsonSerializationException);
    }

    @Test
    @DisplayName("serializing class without JsonSerializable annotation should" +
            " throw JsonSerializationException")
    void serializeBarePojo() {
        Student student = new Student("John", "Smith", 23);

        Throwable exception = assertThrows(JsonSerializationException.class, () ->
                jsonSerializer.serializePojoObject(student));

        assertTrue(exception instanceof JsonSerializationException);
    }
}