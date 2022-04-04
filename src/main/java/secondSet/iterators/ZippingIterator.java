package secondSet.iterators;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ZippingIterator<E,T> implements Iterator<Person<String, Integer>>{
    private List<E> nameList;
    private List<T> ageList;
    private List<Person> personList;
    private Iterator<Person> personListIterator;

    public ZippingIterator(Iterator<E> nameListIterator, Iterator<T> ageListIterator) {
        nameList = getStreamFromIterator(nameListIterator).collect(Collectors.toList());
        ageList = getStreamFromIterator(ageListIterator).collect(Collectors.toList());
        Iterator<E> nameIterator = nameList.iterator();
        Iterator<T> ageIterator = ageList.iterator();
        personList = new LinkedList<>();
        while (nameIterator.hasNext() && ageIterator.hasNext()) {
            personList.add(new Person(nameIterator.next(), ageIterator.next()));
        }
        personListIterator = personList.iterator();
    }

    private <E> Stream<E> getStreamFromIterator(Iterator<E> iterator) {
        Spliterator<E> spliterator =
                Spliterators.spliteratorUnknownSize(iterator, 0);
        return StreamSupport.stream(spliterator, false);
    }

    @Override
    public boolean hasNext() {
        if (personListIterator.hasNext()) return true;
        return false;
    }

    @Override
    public Person next() {
        return personListIterator.next();
    }
}
