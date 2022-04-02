package secondSet.iterators.demo;

import secondSet.iterators.BufferingIterator;

import java.util.Iterator;
import java.util.List;

public class BufferingIteratorDemo {
    public static void main(String[] args) {
        Iterator<List<Integer>> iter = new BufferingIterator<>(
                List.of(1, 2, 3, 4, 5).iterator(), 3
        );
        while (iter.hasNext()) {
            List<Integer> batch = iter.next();
            System.out.println(batch);
        }

    }
}
