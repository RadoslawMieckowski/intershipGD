package thirdSet.dynamicProxy.demos;

import thirdSet.dynamicProxy.handlers.StopwatchInvocationHandler;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class MapProxyInstanceDemo {
    public static void main(String[] args) {

        Map<Integer, String> mapProxyInstance = (Map) Proxy.newProxyInstance(
                StopwatchInvocationHandler.class.getClassLoader(), new Class[] { Map.class },
                new StopwatchInvocationHandler(new HashMap<>()));

        mapProxyInstance.put(1, "one");
        mapProxyInstance.get(1);
    }
}
