package secondSet.iterators;

import secondSet.iterators.models.Person;
import secondSet.utilities.IteratorHandler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

public class ZippingIterator<String, Integer> implements Iterator<Person>{
    private List<Person> personList;
    private Iterator<Person> personListIterator;
    private List<String> nameList;
    private List<Integer> ageList;

    public ZippingIterator(Iterator<String> nameIterator,
                           Iterator<Integer> ageIterator,
                           BiFunction<String, Integer, Person> combiner) {
        personList = new LinkedList<>();
        nameList = IteratorHandler.getStreamFromIterator(nameIterator)
                .toList();
        ageList = IteratorHandler.getStreamFromIterator(ageIterator)
                .toList();
        int sizePersonList = defineSizeOfPersonList();
        for(int i = 0; i < sizePersonList; i++) {
            personList.add(combiner.apply(nameList.get(i), ageList.get(i)));
        }
        personListIterator = personList.iterator();
    }

    private int defineSizeOfPersonList() {
        if (nameList.size() < ageList.size()) return nameList.size();
        else return ageList.size();
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
