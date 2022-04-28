package thirdSet.generics;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

class ResultTest {

    @Test
    @DisplayName("ok method should return new instance of Return type")
    void okTest() {
        Result<String, Exception> actualResult = Result.ok("Java");
        Class<?> aClass = actualResult.getClass();
        Field[] actualFields = aClass.getDeclaredFields();
        List<String> actualFieldNames = new ArrayList<>();
        for (Field field : actualFields)
            actualFieldNames.add(field.getName());

        List<String> expectedNamesOfFields = List.of("operationResult", "exception");

        assertEquals("Result", aClass.getSimpleName());
        assertThat(actualFieldNames.equals(expectedNamesOfFields.toArray()));
    }

    @Test
    void err() {
    }

    @Test
    void of() {
    }

    @Test
    void map() {
    }

    @Test
    void mapErr() {
    }

    @Test
    void orElse() {
    }

    @Test
    void unwrap() {
    }
}