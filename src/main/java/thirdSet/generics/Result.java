package thirdSet.generics;

import java.util.function.Function;
import java.util.function.Supplier;

public final class Result<T, E extends Exception> {
    private final T operationResult;
    private final E exception;

    public Result(T operationResult, E exception) {
        this.operationResult = operationResult;
        this.exception = exception;
    }

    public static <T, E extends Exception> Result<T, E> ok(T operationResult) {
        return new Result(operationResult, null);
    }

    public static <T, E extends Exception> Result<T, E> err(E exception) {
        return new Result<>(null, exception);
    }

    public static <T> Result<T, ?> of(Supplier<T> supplier) {
        try {
            return ok(supplier.get());
        } catch (Exception exception) {
            return err(exception);
        }
    }

    public <N> Result<N, E> map(Function<T, N> mapper) throws IllegalAccessException {
        if (mapper == null) throw new IllegalAccessException("arg can't be null!");
        if(operationResult != null) {
            return new Result<>(mapper.apply(operationResult), null);
        } else return new Result<>(null, exception);
    }

    public <N extends Exception> Result<T, N> mapErr(Function<E, N> mapper) throws IllegalAccessException {
        if (mapper == null) throw new IllegalAccessException("arg can't be null!");
        if(exception != null) {
            return new Result<>(null, mapper.apply(exception));
        } else return new Result<>(operationResult, null);
    }
}