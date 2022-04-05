package secondSet.iterators;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class BusinessDaysIteratorTest {

    BusinessDaysIterator businessDaysIterator;

    @BeforeEach
    void setUp() {
       businessDaysIterator =
                new BusinessDaysIterator<>(LocalDate.of(2022, 1, 1));
    }
    @Test
    void hasNext() {
        assertTrue(businessDaysIterator.hasNext());
    }

    @Test
    @DisplayName("next method should return LocalDate object")
    void next() {
        LocalDate localDate;
        for (int i = 1; i <= 30; i++) {
            businessDaysIterator.next();
        }
        localDate = businessDaysIterator.next();

        assertEquals(LocalDate.of(2022, 2, 14),
                localDate, "hasNext method should return all elements!");
    }

    @Test
    @DisplayName("hasNext method should return false if iterator has nothing to iterate on!")
    void testContructorWithNullParam() {
        LocalDate localDate = null;
        Throwable exception = assertThrows(NullPointerException.class,
                () -> new BusinessDaysIterator(localDate));
        assertTrue(exception instanceof NullPointerException);

    }

}