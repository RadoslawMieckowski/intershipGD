package secondSet.iterators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class BufferingIteratorTest {

    BufferingIterator<Integer> bufferingIterator;

    @BeforeEach
    void setUp() {
        List<Integer> list1 = List.of();
        bufferingIterator = new BufferingIterator<>(list1.iterator(),3);
    }

    @Test
    @DisplayName("hasNext method should return all elements")
    void next() {
        BufferingIterator<Integer> bufferingIterator =
                new BufferingIterator<>(List.of(1,2,3,4,5).iterator(),2);
        long counterOfElements = 0;
        while (bufferingIterator.hasNext()) {
            bufferingIterator.next();
            counterOfElements++;
        }

        assertEquals(3, counterOfElements, "hasNext method should return all elements!");
    }

    @Test
    @DisplayName("hasNext method should return false if iterator has nothing to iterate on!")
    void hasNextOnEmptyIterator() {
        assertTrue("hasNext method should return false if iterator has nothing to iterate on!",
                bufferingIterator.hasNext() == false);
    }

    @Test
    void nextOnEmptyIterator() {
        Throwable exception = assertThrows(NoSuchElementException.class,
                () -> bufferingIterator.next());
        assertTrue(exception instanceof NoSuchElementException);
    }
}