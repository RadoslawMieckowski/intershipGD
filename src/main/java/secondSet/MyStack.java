package secondSet;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyStack<Integer> {
private List<Integer> innerList;
private int size;

public MyStack(int size) {
    innerList = new LinkedList<>();
    this.size = size;
}

    public Iterator<Integer> iterator() {
            return innerList.listIterator();
        }

    public Integer peek() {
        if (size == 0) throw new EmptyStackException();
        return innerList.get(0);
    }

    public Integer pop() {
        if (size == 0) throw new EmptyStackException();
        return ((LinkedList<Integer>) innerList).pop();
    }

    public Integer push(Integer element) {
    if (innerList.size() == size) throw new FullMyStackException("The stack is already full!");
        ((LinkedList<Integer>) innerList).push(element);
        return element;
    }

    public int size() {
        return innerList.size();
    }

}
