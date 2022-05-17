package thirdSet.dynamicProxy.handlers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StopwatchInvocationHandler implements InvocationHandler {
    private Object target;

    public StopwatchInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = method.invoke( target, args);
        long elapsed = System.nanoTime() - start;
        System.out.println("Executing " + method.getName() + " took " + elapsed + " nanos.");
        return result;
    }
}
