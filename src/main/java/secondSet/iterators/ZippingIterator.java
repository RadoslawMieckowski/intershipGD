package secondSet.iterators;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ZippingIterator<E,T> implements Iterator<Person<String, Integer>>{
    private List<E> nameList;
    private List<T> ageList;
    private Iterator<E> nameListIterator;
    private Iterator<T> ageListIterator;
    private List<Person> personList;

    public ZippingIterator(Iterator<E> nameListIterator, Iterator<T> ageListIterator) {
        nameList = getStreamFromIterator(nameListIterator).collect(Collectors.toList());
        ageList = getStreamFromIterator(ageListIterator).collect(Collectors.toList());
        nameListIterator = nameList.iterator();
        ageListIterator = ageList.iterator();
        personList = new LinkedList<>();
        while (nameListIterator.hasNext() && ageListIterator.hasNext()) {
            personList.add(new Person(nameListIterator.next(), ageListIterator.next()));
        }
    }

    private <E> Stream<E> getStreamFromIterator(Iterator<E> iterator) {
        Spliterator<E> spliterator =
                Spliterators.spliteratorUnknownSize(iterator, 0);
        return StreamSupport.stream(spliterator, false);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Person next() {
        return null;
    }
}
