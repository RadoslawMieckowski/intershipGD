package secondSet.iterators;

import org.junit.jupiter.api.*;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class BusinessDaysIteratorTest {

    @Test
    void hasNextTest() {
        BusinessDaysIterator businessDaysIterator =
                new BusinessDaysIterator(LocalDate.of(2022, 1, 1));
        assertTrue(businessDaysIterator.hasNext());
    }

    @Test
    @DisplayName("next method should return LocalDate object")
    void retrieveAllElements() {
        BusinessDaysIterator businessDaysIterator =
                new BusinessDaysIterator(LocalDate.of(2022, 1, 1));
        LocalDate localDate;
        for (int i = 1; i <= 30; i++) {
            businessDaysIterator.next();
        }
        localDate = businessDaysIterator.next();

        assertEquals(LocalDate.of(2022, 2, 14),
                localDate, "hasNext method should return all elements!");
    }

    @Test
    @DisplayName("next should return monday if start day is friday")
    void startDayFridayTest() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        while(startDate.getDayOfWeek() != DayOfWeek.FRIDAY) {
            startDate = startDate.plusDays(1);
        }
        BusinessDaysIterator businessDaysIterator = new BusinessDaysIterator(startDate);
        DayOfWeek actualDay = null;
        if (businessDaysIterator.hasNext()) {
            actualDay = businessDaysIterator.next()
                    .getDayOfWeek();
        }

        assertEquals(DayOfWeek.MONDAY, actualDay, "next should return Monday!");
    }

    @Test
    @DisplayName("next should return monday if start day is saturday")
    void startDaySaturdayTest() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        while(startDate.getDayOfWeek() != DayOfWeek.SATURDAY) {
            startDate = startDate.plusDays(1);
        }
        BusinessDaysIterator businessDaysIterator = new BusinessDaysIterator(startDate);
        DayOfWeek actualDay = null;
        if (businessDaysIterator.hasNext()) {
            actualDay = businessDaysIterator.next()
                    .getDayOfWeek();
        }

        assertEquals(DayOfWeek.MONDAY, actualDay, "next should return Monday!");
    }

    @Test
    @DisplayName("next should return monday if start day is sunday")
    void startDaySundayTest() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        while(startDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
            startDate = startDate.plusDays(1);
        }
        BusinessDaysIterator businessDaysIterator = new BusinessDaysIterator(startDate);
        DayOfWeek actualDay = null;
        if (businessDaysIterator.hasNext()) {
            actualDay = businessDaysIterator.next()
                    .getDayOfWeek();
        }

        assertEquals(DayOfWeek.MONDAY, actualDay, "next should return Monday!");
    }

    @Test
    @DisplayName("next should return monday if start day is friday")
    void startDayThursdayTest() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        while(startDate.getDayOfWeek() != DayOfWeek.THURSDAY) {
            startDate = startDate.plusDays(1);
        }
        BusinessDaysIterator businessDaysIterator = new BusinessDaysIterator(startDate);
        DayOfWeek actualDay = null;
        if (businessDaysIterator.hasNext()) {
            actualDay = businessDaysIterator.next()
                    .getDayOfWeek();
        }

        assertEquals(DayOfWeek.FRIDAY, actualDay, "next should return Friday!");
    }

    @Test
    @DisplayName("next should return monday if start day is wednesday")
    void startDayWednesdayTest() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        while(startDate.getDayOfWeek() != DayOfWeek.WEDNESDAY) {
            startDate = startDate.plusDays(1);
        }
        BusinessDaysIterator businessDaysIterator = new BusinessDaysIterator(startDate);
        DayOfWeek actualDay = null;
        if (businessDaysIterator.hasNext()) {
            actualDay = businessDaysIterator.next()
                    .getDayOfWeek();
        }

        assertEquals(DayOfWeek.THURSDAY, actualDay, "next should return Thursday!");
    }

    @Test
    @DisplayName("next should return monday if start day is friday")
    void startDayTuesdayTest() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        while(startDate.getDayOfWeek() != DayOfWeek.TUESDAY) {
            startDate = startDate.plusDays(1);
        }
        BusinessDaysIterator businessDaysIterator = new BusinessDaysIterator(startDate);
        DayOfWeek actualDay = null;
        if (businessDaysIterator.hasNext()) {
            actualDay = businessDaysIterator.next()
                    .getDayOfWeek();
        }

        assertEquals(DayOfWeek.WEDNESDAY, actualDay, "next should return Wednesday!");
    }

    @Test
    @DisplayName("next should return monday if start day is friday")
    void startDayMondayTest() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        while(startDate.getDayOfWeek() != DayOfWeek.MONDAY) {
            startDate = startDate.plusDays(1);
        }
        BusinessDaysIterator businessDaysIterator = new BusinessDaysIterator(startDate);
        DayOfWeek actualDay = null;
        if (businessDaysIterator.hasNext()) {
            actualDay = businessDaysIterator.next()
                    .getDayOfWeek();
        }

        assertEquals(DayOfWeek.TUESDAY, actualDay, "next should return Tuesday!");
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