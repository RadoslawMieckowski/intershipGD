package secondSet.iterators;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BufferingIterator<E> implements Iterator<List<E>> {
    private List<List<E>> listOfLists;
    private Iterator<List<E>> listIterator;
    private int batchSize;
    private int addedElements = 0;

    public BufferingIterator(Iterator<E> iterator, int batchSize) {
        this.batchSize = batchSize;
        listOfLists = createListOfLists(iterator);
        listIterator = listOfLists.iterator();
    }

    private List<List<E>> createListOfLists(Iterator<E> iterator) {
        listOfLists = new LinkedList<>();
        while (iterator.hasNext()) {
            listOfLists.add(fillSubList(iterator));
        }
        return listOfLists;
    }

    private List<E> fillSubList(Iterator<E> iterator) {
        List<E> subList = new LinkedList<>();
        while (iterator.hasNext() && addedElements < batchSize){
            subList.add(iterator.next());
            addedElements++;
        }
        addedElements = 0;
        return subList;
    }

    @Override
    public boolean hasNext() {
        if (listIterator.hasNext()) return true;
        return false;
    }

    @Override
    public List<E> next() {
        return listIterator.next();
    }
}
