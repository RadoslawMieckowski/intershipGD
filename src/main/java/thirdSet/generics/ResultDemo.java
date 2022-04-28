package thirdSet.generics;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public class ResultDemo {
    public static void main(String[] args) {
        Result<Integer, Exception> resultOk = Result.ok(3);

        Result<Integer, Exception> resultException = Result.err(new Exception());

        Supplier<LocalDateTime> supplierValue = () -> LocalDateTime.now();
        Result<LocalDateTime, ?> resultOfValue = Result.of(supplierValue);

        Supplier<Integer> supplierException = () -> 3/0;
        Result<Integer, ?> resultOfException = Result.of(supplierException);
    }
}
