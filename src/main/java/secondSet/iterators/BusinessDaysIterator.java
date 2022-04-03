package secondSet.iterators;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BusinessDaysIterator<E> implements Iterator<LocalDate> {
private List<? extends LocalDate> localDateList;
private Iterator<? extends LocalDate> localDateIterator;

    public BusinessDaysIterator(E localDate) {
        localDateList = getListOfDates(localDate);
        localDateIterator = localDateList.iterator();
    }

    private List<LocalDate> getListOfDates(E localDate) {
        LocalDate localDate1 = (LocalDate) localDate;
        List<LocalDate> localDateList = null;
        return localDateList = Stream
                .iterate(localDate1, nextLocalDate -> nextLocalDate.plusDays(1))
                .limit(100)
                .filter(date -> date.getDayOfWeek().getValue() < 6)
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasNext() {
        if (localDateIterator.hasNext()) return true;
        return false;
    }
    @Override
    public LocalDate next() {
        return localDateIterator.next();
    }
}
