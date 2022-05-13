package thirdSet.generics;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Result<T, E extends Exception> {
    private final T operationResult;
    private final E exception;

    private Result(T operationResult, E exception) {
        this.operationResult = operationResult;
        this.exception = exception;
    }

    public T getOperationResult() {
        return operationResult;
    }

    public E getException() {
        return exception;
    }

    public static <T, E extends Exception> Result<T, E> ok(T operationResult) {
        return new Result(
                Objects.requireNonNull(operationResult, "operationResult can't be null!"),
                null);
    }

    public static <T, E extends Exception> Result<T, E> err(E exception) {
        return new Result<>(
                null,
                Objects.requireNonNull(exception, "exception can't be null!"));
    }

    public static <T> Result<T, ?> of(Supplier<T> supplier) {
        Objects.requireNonNull(supplier, "supplier can't be null!");
        try {
            return ok(supplier.get());
        } catch (Exception exception) {
            return err(exception);
        }
    }

    public <N> Result<N, E> map(Function<T, N> mapper) {
        Objects.requireNonNull(mapper, "arg can't be null!");
        if(operationResult != null) {
            return new Result<>(mapper.apply(operationResult), null);
        } else return new Result<>(null, exception);
    }

    public <N extends Exception> Result<T, N> mapErr(Function<E, N> mapper) {
        Objects.requireNonNull(mapper, "arg can't be null!");
        if(exception != null) {
            return new Result<>(null, mapper.apply(exception));
        } else return new Result<>(operationResult, null);
    }

    public T orElse(T value) {
        Objects.requireNonNull(value, "arg can't be null!");
        if (operationResult != null) return operationResult;
        return value;
    }

    public T unwrap() {
        if (exception != null) throw new RuntimeException(exception);
        return operationResult;
    }
}