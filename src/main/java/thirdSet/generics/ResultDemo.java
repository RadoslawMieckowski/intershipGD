package thirdSet.generics;

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Supplier;

public class ResultDemo {
    public static void main(String[] args) {
        Result<String, Exception> resultOk = Result.ok("Hello");

        Result<Integer, Exception> resultException = Result.err(new Exception());

        Supplier<LocalDateTime> supplierValue = () -> LocalDateTime.now();
        Result<LocalDateTime, ?> resultOfValue = Result.of(supplierValue);

        Supplier<Integer> supplierException = () -> 3/0;
        Result<Integer, ?> resultOfException = Result.of(supplierException);

        Function<String, Integer> stringToIntegerFunction = (x) -> x.length();
        Result<Integer, Exception> mapResult = resultOk.map(stringToIntegerFunction);
    }
}
