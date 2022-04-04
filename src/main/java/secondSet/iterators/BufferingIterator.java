package secondSet.iterators;

import secondSet.utilities.IteratorHandler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BufferingIterator<E> implements Iterator<List<E>> {
    private List<List<E>> listOfLists;
    private Iterator<List<E>> listIterator;
    private int batchSize;
    private int addedElements = 0;

    public BufferingIterator(Iterator<E> iterator, int batchSize) {
        List<E> copiedList = createList(iterator);
        this.batchSize = batchSize;
        listOfLists = createListOfLists(copiedList ,copiedList.iterator());
        listIterator = listOfLists.iterator();
    }

    private List<E> createList(Iterator<E> iterator) {
        List<E> copiedList = IteratorHandler
                .getStreamFromIterator(iterator)
                .collect(Collectors.toList());
        return copiedList;
    }

    private int getNumberOfSubLists (List<E> list) {
        return list.size() % batchSize == 0 ?
                list.size()/batchSize : (list.size()/batchSize) + 1;
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

    private List<List<E>> createListOfLists(List<E> list, Iterator<E> iterator) {
        listOfLists = new LinkedList<>();
        int numberOfLists = getNumberOfSubLists(list);
        List<E> subList;
        while (numberOfLists > 0) {
            subList = fillSubList(iterator);
            numberOfLists--;
            listOfLists.add(subList);
        }
        return listOfLists;
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
