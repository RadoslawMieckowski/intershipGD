package secondSet.utilities;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class IteratorHandler {
    private IteratorHandler() {}

    public static <E> Stream<E> getStreamFromIterator(Iterator<E> iterator) {
        Spliterator<E> spliterator =
                Spliterators.spliteratorUnknownSize(iterator, 0);
        return StreamSupport.stream(spliterator, false);
    }
}
