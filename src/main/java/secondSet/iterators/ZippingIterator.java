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
        nameList = IteratorHandler
                .getStreamFromIterator(nameListIterator)
                .collect(Collectors.toList());
        ageList = IteratorHandler
                .getStreamFromIterator(ageListIterator)
                .collect(Collectors.toList());
        Iterator<E> nameIterator = nameList.iterator();
        Iterator<T> ageIterator = ageList.iterator();
        fillPersonList(nameIterator, ageIterator);
        personListIterator = personList.iterator();
    }

    private void fillPersonList(Iterator<E> nameIterator, Iterator<T> ageIterator) {
        personList = new LinkedList<>();
        while (nameIterator.hasNext() && ageIterator.hasNext()) {
            personList.add(new Person(nameIterator.next(), ageIterator.next()));
        }
    }

    @Override
    public boolean hasNext() {
       return personListIterator.hasNext();
    }

    @Override
    public Person next() {
        return personListIterator.next();
    }
}
