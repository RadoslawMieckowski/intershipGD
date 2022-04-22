package thirdSet.dynamicProxy.demos;

import thirdSet.dynamicProxy.handlers.StopwatchInvocationHandler;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class MapProxyInstanceDemo {
    public static void main(String[] args) {

        Map mapProxyInstance = (Map) Proxy.newProxyInstance(
                StopwatchInvocationHandler.class.getClassLoader(), new Class[] { Map.class },
                new StopwatchInvocationHandler(new HashMap<>()));

        mapProxyInstance.putAll(Map.of(1,"one",2, "two",3, "three"));
        mapProxyInstance.get("two");
    }
}
