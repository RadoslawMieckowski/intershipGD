package thirdSet.generics;

import org.junit.jupiter.api.Test;

import java.io.IOException;
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
    void okMethodShouldReturnNewInstanceOfReturnType() {
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
    void okMethodWithNullArgShouldThrowNullPointerException() {
        assertThatThrownBy(() -> Result.ok(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("operationResult can't be null!");
    }

    @Test
    void errMethodShouldReturnNewInstanceOfReturnType() {
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
    void errMethodWithNullArgShouldThrowNullPointerException() {
        assertThatThrownBy(() -> Result.err(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("exception can't be null!");
    }

    @Test
    void ofMethodShouldReturnNewInstanceOfReturnType() {
        Supplier<LocalDateTime> supplierValue = () -> LocalDateTime.now();
        Result<LocalDateTime, ?> actualResult = Result.of(supplierValue);
        if(actualResult.getOperationResult() != null) {
            okMethodShouldReturnNewInstanceOfReturnType();
        } else {
            errMethodShouldReturnNewInstanceOfReturnType();
        }
    }

    @Test
    void ofMethodWithNullArgShouldThrowNullPointerException() {
        assertThatThrownBy(() -> Result.of(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("supplier can't be null!");
    }

    @Test
    void mapMethodShouldReturnNewResultWithNotNullOperationResultAndExceptionSetToNull() {
        Result<String, Exception> resultOk = Result.ok("Hello");
        Function<String, Integer> stringToIntegerFunction = (x) -> x.length();
        Result<Integer, Exception> actualResult = resultOk.map(stringToIntegerFunction);

        assertEquals(Integer.valueOf(5), actualResult.getOperationResult());
        assertEquals(null, actualResult.getException());
    }

    @Test
    void mapMethodShouldReturnNewResultWithNotNullExceptionAndOperationResultSetToNull() {
        Result<String, Exception> resultErr = Result.err(new RuntimeException());
        Function<String, Integer> stringToIntegerFunction = (x) -> x.length();
        Result<Integer, Exception> actualResult = resultErr.map(stringToIntegerFunction);

        assertNotEquals(null, actualResult.getException());
        assertEquals(null, actualResult.getOperationResult());
    }

    @Test
    void mapMethodWithNullArgShouldThrowNullPointerException() {
        Result<String, Exception> resultOk = Result.ok("Hello");
        assertThatThrownBy(() -> resultOk.map(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("arg can't be null!");
    }

    @Test
    void mapErrAfterOKTest() {
        Result<String, Exception> resultOK = Result.ok("Hello");
        Result<String, IOException> actualResult = resultOK.mapErr(x -> new IOException());

        assertThat(actualResult.getException() == null);
        assertThat(actualResult.getOperationResult())
                .isEqualTo("Hello");
    }

    @Test
    void mapErrAfterErrTest() {
        Result<String, Exception> resultErr = Result.err(new RuntimeException());
        Result<String, IOException> actualResult = resultErr.mapErr(
                x -> new IOException("mapped exception"));

        assertThat(actualResult.getOperationResult() == null);
        assertThat(actualResult.getException().
                getMessage().equals("mapped exception"));
    }

    @Test
    void mapErrNullTest() {
        Result<String, Exception> resultErr = Result.err(new RuntimeException());

        assertThatThrownBy(() -> resultErr.mapErr(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("arg can't be null!");
    }

    @Test
    void orElseTest() {
        Result<String, Exception> resultOK = Result.ok("Hello");
        String actualValueOk = resultOK.orElse("Java");
        Result<String, Exception> resultErr = Result.err(new RuntimeException());
        String actualValueErr = resultErr.orElse("Spring");

        assertThat(actualValueOk.equals("Hello"));
        assertThat(actualValueErr.equals("C++"));
    }

    @Test
    void orElseNullTest() {
        Result<String, Exception> resultOK = Result.ok("Hello");

        assertThatThrownBy(() -> resultOK.orElse(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("arg can't be null!");
    }

    @Test
    void unwrapAfterOkTest() {
        Result<String, Exception> resultOK = Result.ok("Hello");
        String actualValueOk = resultOK.unwrap();

        assertThat(actualValueOk.equals("Hello"));
    }

    @Test
    void unwrapAfterErrTest() {
        Result<String, Exception> resultErr = Result.err(new RuntimeException());

        assertThatThrownBy(() ->resultErr.unwrap())
                .isInstanceOf(RuntimeException.class);
    }
}