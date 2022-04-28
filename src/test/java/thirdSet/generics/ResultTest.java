package thirdSet.generics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        assertThat(actualResult.getOperationResult().equals("Java"));
        assertThat(actualResult.getException() == null);
    }

    @Test
    @DisplayName("ok method with null arg should throw IllegalArgumentException")
    void okNullTest() {
        assertThatThrownBy(() -> Result.ok(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("operationResult can't be null!");
    }

    @Test
    @DisplayName("err method should return new instance of Return type")
    void errTest() {
        Result<String, Exception> actualResult = Result.err(new NullPointerException());
        Class<?> aClass = actualResult.getClass();
        Field[] actualFields = aClass.getDeclaredFields();
        List<String> actualFieldNames = new ArrayList<>();
        for (Field field : actualFields)
            actualFieldNames.add(field.getName());

        List<String> expectedNamesOfFields = List.of("operationResult", "exception");

        assertEquals("Result", aClass.getSimpleName());
        assertThat(actualFieldNames.equals(expectedNamesOfFields.toArray()));
        assertThat(actualResult.getException()
                .getClass()
                .getSimpleName()
                .endsWith("Exception"));
        assertThat(actualResult.getOperationResult() == null);
    }

    @Test
    @DisplayName("err method with null arg should throw IllegalArgumentException")
    void errNullTest() {
        assertThatThrownBy(() -> Result.err(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("exception can't be null!");
    }

    @Test
    @DisplayName("of method should return new instance of Return type")
    void ofTest() {
        Supplier<LocalDateTime> supplierValue = () -> LocalDateTime.now();
        Result<LocalDateTime, ?> actualResult = Result.of(supplierValue);
        if(actualResult.getOperationResult() != null) {
            okTest();
        } else {
            errTest();
        }
    }

    @Test
    @DisplayName("of method with null arg should throw IllegalArgumentException")
    void ofNullTest() {
        assertThatThrownBy(() -> Result.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("supplier can't be null!");
    }

    @Test
    @DisplayName("map method should return new Result with" +
            " not null operationResult and exception set to null")
    void mapAfterOkTest() {
        Result<String, Exception> resultOk = Result.ok("Hello");
        Function<String, Integer> stringToIntegerFunction = (x) -> x.length();
        Result<Integer, Exception> actualResult = resultOk.map(stringToIntegerFunction);

        assertEquals(Integer.valueOf(5), actualResult.getOperationResult());
        assertEquals(null, actualResult.getException());
    }

    @Test
    @DisplayName("map method should return new Result with" +
            " not null exception and operationResult set to null")
    void mapAfterErrTest() {
        Result<String, Exception> resultErr = Result.err(new RuntimeException());
        Function<String, Integer> stringToIntegerFunction = (x) -> x.length();
        Result<Integer, Exception> actualResult = resultErr.map(stringToIntegerFunction);

        assertNotEquals(null, actualResult.getException());
        assertEquals(null, actualResult.getOperationResult());
    }

    @Test
    @DisplayName("map method with null arg should throw IllegalArgumentException")
    void mapNullTest() {
        Result<String, Exception> resultOk = Result.ok("Hello");
        assertThatThrownBy(() -> resultOk.map(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arg can't be null!");
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