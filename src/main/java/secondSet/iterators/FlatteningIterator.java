package secondSet.iterators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static secondSet.utilities.IteratorHandler.getStreamFromIterator;

public class FlatteningIterator<E> implements Iterator<E> {

    private List<E> listOfElements;
    private Iterator<E> iterator;

    public FlatteningIterator(Iterator<E>... iterators) {
        listOfElements = Arrays.stream(iterators)
                .flatMap(iterator ->getStreamFromIterator(iterator))
                .collect(Collectors.toList());
        iterator = listOfElements.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public E next() {
        return iterator.next();
    }
}
