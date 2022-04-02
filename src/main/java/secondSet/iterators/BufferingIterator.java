package secondSet.iterators;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BufferingIterator<E> implements Iterator<List<E>> {
    private List<List<E>> listOfLists;
    private Iterator<List<E>> listIterator;

    public BufferingIterator(Iterator<E> iterator, int batchSize) {
        List<E> copiedList = new LinkedList<>();
        while (iterator.hasNext()) {
            copiedList.add(iterator.next());
//        System.out.println(copiedList);
        }
        Iterator<E> copiedListIterator = copiedList.iterator();
        listOfLists = new LinkedList<>();
            int pointer = 0;
            List<E>smallList;
            int numberOfLists = copiedList.size() % batchSize == 0 ? copiedList.size()/batchSize : (copiedList.size()/batchSize) + 1;
            while (numberOfLists > 0) {
            smallList= new LinkedList<>();
            numberOfLists--;
            while (copiedListIterator.hasNext() && pointer < batchSize){
            smallList.add(copiedListIterator.next());
            pointer++;
            }
            pointer = 0;
            listOfLists.add(smallList);
        }
            listIterator = listOfLists.iterator();
        //list.stream().limit(batchSize).collect(Collectors.toList());
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
