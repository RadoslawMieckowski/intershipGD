package secondSet.iterators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FlatteningIteratorTest {

    @Test
    @DisplayName("hasNext method should return all elements")
    void retrieveAllElements() {
        FlatteningIterator<Integer> flatteningIterator = new FlatteningIterator<>(
                List.of(1, 2).iterator(),
                List.of(3, 4, 5).iterator(),
                List.of(6, 7, 8, 9).iterator()
        );
        long sum = 0;
        while (flatteningIterator.hasNext()) {
            sum += flatteningIterator.next();
        }

        assertEquals(45, sum, "hasNext method should return all elements!");
    }


    @Test
    @DisplayName("hasNext method should return false if iterator has nothing to iterate on!")
    void hasNextOnEmptyIterator() {
        List<Integer> list1 = List.of();
        List<Integer> list2 = List.of();
        FlatteningIterator<Integer> flatteningIterator = new FlatteningIterator<>(
                list1.iterator(),
                list2.iterator()
        );

        assertTrue("hasNext method should return false if iterator has nothing to iterate on!",
                flatteningIterator.hasNext() == false);
    }

    @Test
    void nextOnEmptyIterator() {
        List<Integer> list1 = List.of();
        List<Integer> list2 = List.of();
        FlatteningIterator<Integer> flatteningIterator = new FlatteningIterator<>(
                list1.iterator(),
                list2.iterator()
        );

        Throwable exception = assertThrows(NoSuchElementException.class,
                () -> flatteningIterator.next());
        assertTrue(exception instanceof NoSuchElementException);
    }
}