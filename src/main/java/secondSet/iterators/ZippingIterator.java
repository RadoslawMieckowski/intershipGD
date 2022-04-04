package secondSet.iterators;

import java.util.Iterator;
import java.util.List;

public class ZippingIterator<E> implements Iterator<Person>{
    private List<String> nameList;
    private List<Integer> ageList;
    private List<Person> personList;

    public ZippingIterator(Iterator<String> nameListIterator, Iterator<Integer> ageListIterator) {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public E next() {
        return null;
    }
}
