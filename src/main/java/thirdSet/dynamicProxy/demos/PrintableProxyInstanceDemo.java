package thirdSet.dynamicProxy.demos;

import thirdSet.dynamicProxy.handlers.StopwatchInvocationHandler;
import thirdSet.dynamicProxy.interfaces.Printable;
import thirdSet.dynamicProxy.pojos.Owner;
import thirdSet.dynamicProxy.pojos.Pet;

import java.lang.reflect.Proxy;
import java.util.List;

public class PrintableProxyInstanceDemo {
    public static void main(String[] args) {

        Owner grandma = new Owner("Grandma");
        Pet cat = new Pet("cat", "Sylvester", false);
        Pet bird = new Pet("bird", "Tweety", true);
        cat.setOwner(grandma);
        bird.setOwner(grandma);
        grandma.setPets(List.of(cat, bird));

        Printable printablePetProxyInstance = (Printable) Proxy.newProxyInstance(
                StopwatchInvocationHandler.class.getClassLoader(),
                new Class[] {Printable.class},
                new StopwatchInvocationHandler(cat));

        printablePetProxyInstance.print();

        Printable printableOwnerProxyInstance = (Printable) Proxy.newProxyInstance(
                StopwatchInvocationHandler.class.getClassLoader(),
                new Class[] {Printable.class},
                new StopwatchInvocationHandler(grandma)
        );
        printableOwnerProxyInstance.print();
    }
}
