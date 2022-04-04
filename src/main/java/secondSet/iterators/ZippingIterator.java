package secondSet.iterators;

import secondSet.iterators.models.Person;
import secondSet.utilities.IteratorHandler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ZippingIterator<E,T> implements Iterator<Person<String, Integer>>{
    private List<E> nameList;
    private List<T> ageList;
    private List<Person> personList;
    private Iterator<Person> personListIterator;

    public ZippingIterator(Iterator<E> nameListIterator, Iterator<T> ageListIterator) {
        nameList = IteratorHandler.getStreamFromIterator(nameListIterator).collect(Collectors.toList());
        ageList = IteratorHandler.getStreamFromIterator(ageListIterator).collect(Collectors.toList());
        Iterator<E> nameIterator = nameList.iterator();
        Iterator<T> ageIterator = ageList.iterator();
        personList = new LinkedList<>();
        while (nameIterator.hasNext() && ageIterator.hasNext()) {
            personList.add(new Person(nameIterator.next(), ageIterator.next()));
        }
        personListIterator = personList.iterator();
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
