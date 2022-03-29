package secondSet;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyStack<Integer> {
private List<Integer> innerList;
private int size;

public MyStack(int size) {
    innerList = new LinkedList<Integer>();
    for (int i = 1; i <= size; i++) {
        innerList.add(null);
    }
    size = innerList.size();
}

    public Iterator<Integer> iterator() {
            return innerList.listIterator();
        }

    public Integer peek() {
        if (size == 0) throw new EmptyStackException();
        return innerList.get(size - 1);
    }

    public Integer pop() {
        if (size == 0) throw new EmptyStackException();
        return innerList.remove(size - 1);
    }

    public Integer push(Integer element) {
        return innerList.set(size, element);
    }

    public int size() {
        return innerList.size();
    }

}
