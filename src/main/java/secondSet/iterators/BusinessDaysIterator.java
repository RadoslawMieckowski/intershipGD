package secondSet.iterators;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Iterator;

public class BusinessDaysIterator implements Iterator<LocalDate> {
private LocalDate currentDate;

    public BusinessDaysIterator(LocalDate localDate) {
        if (localDate == null) throw new NullPointerException("Local date must not be null!");
        currentDate = localDate;
    }

    @Override
    public boolean hasNext() {
        return true;
    }
    @Override
    public LocalDate next() {
        currentDate = currentDate.plusDays(1);
        if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY) currentDate = currentDate.plusDays(2);
        if (currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) currentDate = currentDate.plusDays(1);
        return currentDate;
    }
}
