package secondSet.iterators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BufferingIteratorTest {

    @Test
    @DisplayName("next method should return all elements")
    void next() {
        BufferingIterator<Integer> bufferingIterator =
                new BufferingIterator<>(List.of(1,2,3,4,5).iterator(),2);
        long counterOfElements = 0;
        while (bufferingIterator.hasNext()) {
            bufferingIterator.next();
            counterOfElements++;
        }

        assertEquals(3, counterOfElements, "next method should return all elements!");
    }

    @Test
    @DisplayName("hasNext method should return false if iterator has nothing to iterate on!")
    void hasNextOnEmptyIterator() {
        BufferingIterator<Integer> bufferingIterator = new BufferingIterator<>(
                new LinkedList<Integer>().iterator(), 4);
        assertTrue("hasNext method should return false if iterator has nothing to iterate on!",
                bufferingIterator.hasNext() == false);
    }

    @Test
    @DisplayName("next method should throw NoSuchElementException if iterator has nothing to iterate on!")
    void nextOnEmptyIterator() {
        BufferingIterator<Integer> bufferingIterator = new BufferingIterator<>(
                new LinkedList<Integer>().iterator(), 4);
        Throwable exception = assertThrows(NoSuchElementException.class,
                () -> bufferingIterator.next());
        assertTrue(exception instanceof NoSuchElementException);
    }
}