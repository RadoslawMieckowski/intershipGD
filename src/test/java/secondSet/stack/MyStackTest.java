package secondSet.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyStackTest {

   MyStack myStack;

    @BeforeEach
    void setUp() {
        myStack = new MyStack<>(4);
    }

    @Test
    @DisplayName("Creating iterator of myStack instance should work")
    void iterator() {
        Iterator<Integer> iterator = myStack.iterator();

    }

    @Test
    void peek() {
    }

    @Test
    void pop() {
    }

    @RepeatedTest(4)
    @DisplayName("pushing elements to myStack instance should work")
    void push() {
        assertEquals(myStack.push(7), 7, "Regular pushing should work");
    }

    @Test
    void size() {
        myStack.push(2);
        myStack.push(3);
        myStack.push(5);
        myStack.push(7);
        assertEquals( 4, myStack.size(), "size() method should work");
    }
}