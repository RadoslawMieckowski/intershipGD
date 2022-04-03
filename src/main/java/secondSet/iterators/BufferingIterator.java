package secondSet.iterators;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BufferingIterator<E> implements Iterator<List<E>> {
    private List<List<E>> listOfLists;
    private Iterator<List<E>> listIterator;
    private int batchSize;

    public BufferingIterator(Iterator<E> iterator, int batchSize) {
        List<E> copiedList = createList(iterator);
        Iterator<E> copiedListIterator = copiedList.iterator();
        this.batchSize = batchSize;
        listOfLists = new LinkedList<>();
            int addedElements = 0;
            int numberOfLists = getNumberOfSubLists(copiedList);
            List<E> subList;
            while (numberOfLists > 0) {
            subList = new LinkedList<>();
            numberOfLists--;
            while (copiedListIterator.hasNext() && addedElements < batchSize){
            subList.add(copiedListIterator.next());
            addedElements++;
            }
            addedElements = 0;
            listOfLists.add(subList);
        }
            listIterator = listOfLists.iterator();
        //list.stream().limit(batchSize).collect(Collectors.toList());
    }

    private List<E> createList(Iterator<E> iterator) {
        List<E> copiedList = new LinkedList<>();
        while (iterator.hasNext()) {
            copiedList.add(iterator.next());
        }
        return copiedList;
    }

    private int getNumberOfSubLists (List<E> list) {
        return list.size() % batchSize == 0 ?
                list.size()/batchSize : (list.size()/batchSize) + 1;
    }

    private List<E> fillSubList(Iterator<E> iterator) {
        List<E> subList = new LinkedList<>();
        int addedElements = 0;
        while (iterator.hasNext() && addedElements < batchSize){
            subList.add(iterator.next());
            addedElements++;
        }
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
