package secondSet;

import java.util.ArrayList;
import java.util.Iterator;

public class MyStackDemo {
    public static void main(String[] args) {
        MyStack myStack = new MyStack<>(3);
        myStack.push(1);
        myStack.push(3);
        myStack.push(2);
        myStack.pop();
        Iterator<Integer> iterator = myStack.iterator();
        while (iterator.hasNext()) {
            int number = iterator.next();
            System.out.println(("peek: " + myStack.peek()));
            System.out.println(number);
        }
        System.out.println("size: " + myStack.size());
    }
}
