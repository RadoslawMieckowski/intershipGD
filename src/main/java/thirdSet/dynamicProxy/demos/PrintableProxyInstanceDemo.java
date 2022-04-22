package thirdSet.dynamicProxy.demos;

import thirdSet.dynamicProxy.handlers.StopwatchInvocationHandler;
import thirdSet.dynamicProxy.interfaces.Printable;
import thirdSet.reflection.pojos.Intern;

import java.lang.reflect.Proxy;
import java.util.LinkedHashSet;
import java.util.Set;

public class PrintableProxyInstanceDemo {
    public static void main(String[] args) {

        Intern intern = new Intern("John", "Smith", 23,
                new LinkedHashSet<>(Set.of("film", "music")));

        Printable printableProxyInstance = (Printable) Proxy.newProxyInstance(
                StopwatchInvocationHandler.class.getClassLoader(),
                new Class[] {Printable.class},
                new StopwatchInvocationHandler(intern));

        printableProxyInstance.print();
    }
}
