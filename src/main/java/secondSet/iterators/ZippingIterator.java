package secondSet.iterators;

import secondSet.iterators.models.Person;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

public class ZippingIterator<String, Integer> implements Iterator<Person>{
    private List<Person> personList;
    private Iterator<Person> personListIterator;

    public ZippingIterator(Iterator<String> nameIterator,
                           Iterator<Integer> ageIterator,
                           BiFunction<String, Integer, Person> combiner) {
        personList = new LinkedList<>();
        while (nameIterator.hasNext() && ageIterator.hasNext()) {
            personList.add(combiner.apply(nameIterator.next(), ageIterator.next()));
        }
        personListIterator = personList.iterator();
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
