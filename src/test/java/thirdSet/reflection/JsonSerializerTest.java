package thirdSet.reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import thirdSet.reflection.exceptions.JsonSerializationException;
import thirdSet.reflection.pojos.Intern;
import thirdSet.reflection.pojos.Student;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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
                23, (List.of("music", "film")));
        String actualJsonString = jsonSerializer.serializePojoObject(intern);

        String expectedJsonString = "{\"internName\":\"John\", \"surName\":\"Smith\"," +
                " \"internAge\":\"23\", \"hobbies\":\"[music, film]\"}";

        Assertions.assertEquals(expectedJsonString, actualJsonString,
                "serializePojoObject should return JSON string " +
                        "with order according to declared fields in class!");
    }

    @Test
    @DisplayName("serializing null should throw JsonSerializationException")
    void serializeNullTest() {
        Intern intern = null;
        assertThatExceptionOfType(JsonSerializationException.class)
                .isThrownBy(() -> jsonSerializer.serializePojoObject(intern))
                .withMessage("The object to serialize is null");
    }

    @Test
    @DisplayName("serializing class without JsonSerializable annotation should" +
            " throw JsonSerializationException")
    void serializeBarePojoTest() {
        Student student = new Student("John", "Smith", 23);
        assertThatExceptionOfType(JsonSerializationException.class)
                .isThrownBy(() -> jsonSerializer.serializePojoObject(student))
                .withMessage("The class "
                        + Student.class.getSimpleName()
                        + " is not annotated with JsonSerializable");
    }
}