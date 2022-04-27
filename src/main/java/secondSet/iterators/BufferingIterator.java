package secondSet.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BufferingIterator<E> implements Iterator<List<E>> {
    private List<List<E>> listOfLists = new LinkedList<>();
    private Iterator<List<E>> listIterator;
    private int batchSize;
    private int addedElements = 0;

    public BufferingIterator(Iterator<E> iterator, int batchSize) {
        this.batchSize = batchSize;
        createListOfLists(iterator);
        listIterator = listOfLists.iterator();
    }

    private void createListOfLists(Iterator<E> iterator) {
        List<E> subList = new LinkedList<>();
        addedElements = 0;
        boolean isNotOver = true;
        while (addedElements < batchSize){
            if (iterator.hasNext()) {
                subList.add(iterator.next());
                addedElements++;
            }
            else {
                isNotOver = false;
                break;
            }
        }
        if (!subList.isEmpty()) listOfLists.add(subList);
        if (isNotOver) createListOfLists(iterator);
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
