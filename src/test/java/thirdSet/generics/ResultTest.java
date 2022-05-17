package thirdSet.generics;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ResultTest {

    @Test
    void okMethodShouldReturnNewInstanceOfReturnType() {
        Result<String, Exception> actualResult = Result.ok("Java");

        assertThat(actualResult).isInstanceOf(Result.class);
        assertThat(actualResult.getOperationResult()).isEqualTo("Java");
        assertThat(actualResult.getException()).isNull();
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

        assertThat(actualResult).isInstanceOf(Result.class);
        assertThat(actualResult.getException()).isInstanceOf(Exception.class);
        assertThat(actualResult.getOperationResult()).isNull();
    }

    @Test
    void errMethodWithNullArgShouldThrowNullPointerException() {
        assertThatThrownBy(() -> Result.err(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("exception can't be null!");
    }

    @Test
    void ofMethodShouldReturnNewInstanceOfReturnTypeWithOperationResultNotNullAndExceptionSetToNull() {
        LocalDateTime dateTime = LocalDateTime.now();
        Supplier<LocalDateTime> supplierValue = () -> dateTime;
        Result<LocalDateTime, ?> actualResult = Result.of(supplierValue);

        assertThat(actualResult).isInstanceOf(Result.class);
        assertThat(actualResult.getOperationResult()).isEqualTo(dateTime.toString());
        assertThat(actualResult.getException()).isNull();

    }

    @Test
    void ofMethodShouldReturnNewInstanceOfReturnTypeWithNotNullExceptionAndOperationResultSetToNull() {
        Result<Integer, ?> actualResult = Result.of(() ->Integer.valueOf(3/0));

        assertThat(actualResult).isInstanceOf(Result.class);
        assertThat(actualResult.getOperationResult()).isNull();
        assertThat(actualResult.getException()).isInstanceOf(Exception.class);
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

        assertThat(actualResult.getOperationResult()).isEqualTo(5);
        assertThat(actualResult.getException()).isNull();
    }

    @Test
    void mapMethodShouldReturnNewResultWithNotNullExceptionAndOperationResultSetToNull() {
        Result<String, Exception> resultErr = Result.err(new RuntimeException());
        Function<String, Integer> stringToIntegerFunction = (x) -> x.length();
        Result<Integer, Exception> actualResult = resultErr.map(stringToIntegerFunction);

        assertThat(actualResult.getException()).isNotNull();
        assertThat(actualResult.getOperationResult()).isNull();
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

        assertThat(actualResult.getException()).isNull();
        assertThat(actualResult.getOperationResult()).isEqualTo("Hello");
    }

    @Test
    void mapErrAfterErrTest() {
        Result<String, Exception> resultErr = Result.err(new RuntimeException());
        Result<String, IOException> actualResult = resultErr.mapErr(
                x -> new IOException("mapped exception"));

        assertThat(actualResult.getOperationResult()).isNull();
        assertThat(actualResult.getException().
                getMessage()).isEqualTo("mapped exception");
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

        assertThat(actualValueOk).isEqualTo("Hello");
        assertThat(actualValueErr).isEqualTo("Spring");
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

        assertThat(actualValueOk).isEqualTo("Hello");
    }

    @Test
    void unwrapAfterErrTest() {
        Result<String, Exception> resultErr = Result.err(new RuntimeException());

        assertThatThrownBy(() ->resultErr.unwrap())
                .isInstanceOf(RuntimeException.class);
    }
}