package secondSet.iterators;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import secondSet.iterators.models.Person;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ZippingIteratorTest {

    @Test
    @DisplayName("next method should return all elements")
    void nextCounterOfElements() {
        ZippingIterator<String, Integer> zippingIterator = new ZippingIterator<>(
                List.of("John", "Jane", "Jack", "Dennis").iterator(),
                List.of(24, 25, 27, 12, 23).iterator(),
                (name, age) -> new Person(name, age)
        );
        long counterOfElements = 0;
        while (zippingIterator.hasNext()) {
            zippingIterator.next();
            counterOfElements++;
        }

        assertEquals(4, counterOfElements, "next method should return all elements!");
    }

    @Test
    @DisplayName("Checking return type")
    void nextTypeToReturn() {
        ZippingIterator<String, Integer> zippingIterator = new ZippingIterator<>(
                List.of("John", "Jane", "Jack", "Dennis").iterator(),
                List.of(24, 25, 27, 12, 23).iterator(),
                (name, age) -> new Person(name, age)
        );
        Person person = null;
        if (zippingIterator.hasNext()) {
            person = zippingIterator.next();
        }

        assertTrue(person instanceof Person, "next method should return Person type!");
    }

    @Test
    @DisplayName("hasNext method should return false if iterator has nothing to iterate on!")
    void hasNextOnEmptyIterator() {
        List<String> nameList = List.of();
        List<Integer> ageList = List.of();
        ZippingIterator<String, Integer> zippingIterator = new ZippingIterator<>(
                nameList.iterator(),
                ageList.iterator(),
                (name, age) -> new Person(name, age)
        );

        Assert.assertEquals("hasNext method should return false if iterator has nothing to iterate on!",
                false, zippingIterator.hasNext());
    }

    @Test
    @DisplayName("next method should throw NoSuchElementException if iterator has nothing to iterate on!")
    void nextOnEmptyIterator() {
        List<String> nameList = List.of();
        List<Integer> ageList = List.of();
        ZippingIterator<String, Integer> zippingIterator = new ZippingIterator<>(
                nameList.iterator(),
                ageList.iterator(),
                (name, age) -> new Person(name, age)
        );
        Throwable exception = assertThrows(NoSuchElementException.class,
                () -> zippingIterator.next());
        Assert.assertTrue(exception instanceof NoSuchElementException);
    }
}