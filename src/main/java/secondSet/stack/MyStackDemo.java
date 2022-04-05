package secondSet.stack;

import java.util.Iterator;

public class MyStackDemo {
    public static void main(String[] args) {
        MyStack myStack = new MyStack<>(3);
        myStack.push(3);
        myStack.push(3);
        myStack.push(5);
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
