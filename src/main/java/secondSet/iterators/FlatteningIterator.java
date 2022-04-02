package secondSet.iterators;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FlatteningIterator<T> implements Iterator {

    private List<T> listOfElements;
    private Iterator<T> iterator;

    public FlatteningIterator(Iterator<T>... iterators) {
        listOfElements = Arrays.stream(iterators)
                .flatMap(iterator ->getStreamFromIterator(iterator))
                .collect(Collectors.toList());
        iterator = listOfElements.iterator();
    }

    private Stream<T> getStreamFromIterator(Iterator<T> iterator) {
        Spliterator<T> spliterator =
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
    public T next() {
        return iterator.next();
    }
}
