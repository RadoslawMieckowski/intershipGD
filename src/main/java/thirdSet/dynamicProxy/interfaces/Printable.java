package thirdSet.dynamicProxy.interfaces;

public interface Printable {
    default public void print() {
        System.out.println(this);
    }
}
