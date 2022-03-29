package secondSet;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import static java.lang.Integer.valueOf;

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

    public int push(int element) {
    if (innerList.size() == size) throw new FullMyStackException("The stack is already full!");
    if (isPrimeNumber(element) == false) throw new NotPrimeNumberException("Given number is not a prime one!");
    if (innerList.size() == 0) {
        ((LinkedList<Integer>) innerList).push((Integer) valueOf(element));
    } else {
        if (element <= ((int)innerList.get(0))) throw new SmallerNumberException("Given number is smaller than one on the top of the stack!");
        ((LinkedList<Integer>) innerList).push((Integer) valueOf(element));
    }
        return element;
    }

    public int size() {
        return innerList.size();
    }

    private boolean isPrimeNumber(int number) {
        boolean flag = true;
        for(int i = 2; i < number; i++){
            if (number % i == 0){
                flag = false;
                break;
            }
        }
        return flag;
    }

}
