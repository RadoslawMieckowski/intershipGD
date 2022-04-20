package secondSet.iterators;

import secondSet.utilities.IteratorHandler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BufferingIterator<E> implements Iterator<List<E>> {
    private List<List<E>> listOfLists;
    private Iterator<List<E>> listIterator;
    private int batchSize;
    private int addedElements = 0;
    private List<E> localList;
    private Iterator<E> localListIterator;

    public BufferingIterator(Iterator<E> iterator, int batchSize) {
        this.batchSize = batchSize;
        listOfLists = createListOfLists(iterator);
        listIterator = listOfLists.iterator();
    }

    private List<List<E>> createListOfLists(Iterator<E> iterator) {
        localList = createLocalList(iterator);
        localListIterator = localList.iterator();
        listOfLists = new LinkedList<>();
        int numberOfSubLists = getNumberOfSubLists(localListIterator);
        while (numberOfSubLists > 0) {
            listOfLists.add(fillSubList(localListIterator));
            numberOfSubLists--;
        }
        return listOfLists;
    }

    private int getNumberOfSubLists (Iterator<E> iterator) {
        int sizeOfLocalList = localList.size();
        return sizeOfLocalList % batchSize == 0 ?
                sizeOfLocalList / batchSize : (sizeOfLocalList / batchSize) + 1;
    }

    private List<E> createLocalList(Iterator<E> iterator) {
        return IteratorHandler.getStreamFromIterator(iterator).toList();
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
        return listIterator.hasNext();
    }

    @Override
    public List<E> next() {
        return listIterator.next();
    }
}
