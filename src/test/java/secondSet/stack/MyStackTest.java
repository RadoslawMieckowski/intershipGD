package secondSet.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import secondSet.stack.stackExceptions.FullMyStackException;

import java.util.Iterator;

import static org.junit.Assert.assertThrows;
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

    @Test
    @DisplayName("pushing primes increasing elements to myStack instance should work")
    void push() {
        assertEquals(myStack.push(2), 2, "Regular pushing should work");
        assertEquals(myStack.push(3), 3, "Regular pushing should work");
        assertEquals(myStack.push(5), 5, "Regular pushing should work");
        assertEquals(myStack.push(7), 7, "Regular pushing should work");
    }

    @Test
    @DisplayName("pushing more than allowed amount of primes to myStack instance should should throw FullMyStackException")
    void FullMyStackExceptionTesting() {
        myStack.push(2);
        myStack.push(3);
        myStack.push(5);
        myStack.push(7);

        Throwable exception = assertThrows(FullMyStackException.class, () -> myStack.push(11));
        assertEquals("The stack is already full!", exception.getMessage());
    }

    @Test
    @DisplayName("Pushing not prime numbers to myStack instance should throw NotPrimeNumberException")
    void NotPrimeNumberExceptionTesting() {
        Throwable exception = assertThrows(FullMyStackException.class, () -> myStack.push(11));
        assertEquals("Given number is not a prime one!", exception.getMessage());
    }

    @Test
    @DisplayName("Pushing smaller or equal number to myStack instance should SmallerNumberException")
    void SmallerNumberExceptionTesting() {
        myStack.push(7);

        Throwable exception = assertThrows(FullMyStackException.class, () -> myStack.push(3));
        assertEquals("Given number must be greater than one on the top of the stack!", exception.getMessage());
    }

    @Test
    @DisplayName("checking the size of MyStack instance should work")
    void size() {
        myStack.push(2);
        myStack.push(3);
        myStack.push(5);
        myStack.push(7);

        assertEquals( 4, myStack.size(), "size() method should work");
    }
}