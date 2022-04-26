package secondSet.iterators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
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
    void firstIteratorEmptyTest() {
       List<Integer> emptyList = new LinkedList<>();
        FlatteningIterator<Integer> flatteningIterator = new FlatteningIterator<>(
                emptyList.iterator(),
                List.of(3, 4, 5).iterator(),
                List.of(6, 7, 8, 9).iterator()
        );
        boolean isNullFound = false;
        int counterOfReturnedElements = 0;
        while (flatteningIterator.hasNext()) {
            counterOfReturnedElements++;
            Integer value = flatteningIterator.next();
            if (value == null) {
                isNullFound = true;
                break;
            }
        }

        assertEquals(7, counterOfReturnedElements);
        assertEquals(false, isNullFound);
    }

    @Test
    void secondIteratorEmptyTest() {
        List<Integer> emptyList = new LinkedList<>();
        FlatteningIterator<Integer> flatteningIterator = new FlatteningIterator<>(
                List.of(3, 4, 5).iterator(),
                emptyList.iterator(),
                List.of(6, 7, 8, 9).iterator()
        );
        boolean isNullFound = false;
        int counterOfReturnedElements = 0;
        while (flatteningIterator.hasNext()) {
            counterOfReturnedElements++;
            Integer value = flatteningIterator.next();
            if (value == null) {
                isNullFound = true;
                break;
            }
        }

        assertEquals(7, counterOfReturnedElements);
        assertEquals(false, isNullFound);
    }

    @Test
    void thirdIteratorEmptyTest() {
        List<Integer> emptyList = new LinkedList<>();
        FlatteningIterator<Integer> flatteningIterator = new FlatteningIterator<>(
                List.of(3, 4, 5).iterator(),
                List.of(6, 7, 8, 9).iterator(),
                emptyList.iterator()
        );
        boolean isNullFound = false;
        int counterOfReturnedElements = 0;
        while (flatteningIterator.hasNext()) {
            counterOfReturnedElements++;
            Integer value = flatteningIterator.next();
            if (value == null) {
                isNullFound = true;
                break;
            }
        }

        assertEquals(7, counterOfReturnedElements);
        assertEquals(false, isNullFound);
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