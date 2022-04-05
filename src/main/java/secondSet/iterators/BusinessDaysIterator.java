package secondSet.iterators;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BusinessDaysIterator<E> implements Iterator<LocalDate> {
private List<? extends LocalDate> localDateList;
private Iterator<? extends LocalDate> localDateIterator;
LocalDate currentDate = null;

    public BusinessDaysIterator(E localDate) {
        localDateList = getListOfDates(localDate);
        localDateIterator = localDateList.iterator();
    }

    private List<LocalDate> getListOfDates(E localDate) {
        LocalDate localDate1 = (LocalDate) localDate;
        List<LocalDate> localDateList;
        return localDateList = Stream
                .iterate(localDate1, nextLocalDate -> nextLocalDate.plusDays(1))
                .limit(10)
                .filter(date -> date.getDayOfWeek().getValue() < 6)
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasNext() {
        if (localDateIterator.hasNext()) return true;
        currentDate = currentDate.plusDays(1);
        localDateList = getListOfDates((E) currentDate); //if iterator has no more elements to iterate on
        localDateIterator = localDateList.iterator();//the new list and it's iterator is created
        return true;
    }
    @Override
    public LocalDate next() {
        try {
            currentDate = localDateIterator.next();
        } catch (NoSuchElementException exception) {
            hasNext();
           currentDate = localDateIterator.next();
        }
        return currentDate;
    }
}
