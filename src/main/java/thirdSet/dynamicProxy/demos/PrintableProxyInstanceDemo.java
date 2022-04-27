package thirdSet.dynamicProxy.demos;

import thirdSet.dynamicProxy.handlers.StopwatchInvocationHandler;
import thirdSet.dynamicProxy.interfaces.Printable;
import thirdSet.reflection.pojos.Intern;
import thirdSet.reflection.pojos.Mentor;

import java.lang.reflect.Proxy;
import java.util.List;

public class PrintableProxyInstanceDemo {
    public static void main(String[] args) {

        Intern intern1 = new Intern("John", "Smith", 23,
                List.of("film", "music"));
        Intern intern2 = new Intern("Dave", "Douglas", 21,
                List.of("gaming", "music"));

        Printable printableInternProxyInstance = (Printable) Proxy.newProxyInstance(
                StopwatchInvocationHandler.class.getClassLoader(),
                new Class[] {Printable.class},
                new StopwatchInvocationHandler(intern1));

        printableInternProxyInstance.print();

        Mentor mentor = new Mentor("Will", List.of(intern1, intern2));

        Printable  printableMentorProxyInstance = (Printable) Proxy.newProxyInstance(
                StopwatchInvocationHandler.class.getClassLoader(),
                new Class[] {Printable.class},
                new StopwatchInvocationHandler(mentor)
        );
        printableMentorProxyInstance.print();
    }
}
