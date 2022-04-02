package secondSet.iterators;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FlatteningIterator<E> implements Iterator<E> {

    private List<E> listOfElements;
    private Iterator<E> iterator;

    public FlatteningIterator(Iterator<E>... iterators) {
        listOfElements = Arrays.stream(iterators)
                .flatMap(iterator ->getStreamFromIterator(iterator))
                .collect(Collectors.toList());
        iterator = listOfElements.iterator();
    }

    private Stream<E> getStreamFromIterator(Iterator<E> iterator) {
        Spliterator<E> spliterator =
                Spliterators.spliteratorUnknownSize(iterator, 0);
        return StreamSupport.stream(spliterator, false);
    }

    @Override
    public boolean hasNext() {
        if (iterator.hasNext())
        return true;
        return false;
    }

    @Override
    public E next() {
        return iterator.next();
    }
}
