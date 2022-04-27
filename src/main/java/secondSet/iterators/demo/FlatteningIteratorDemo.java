package secondSet.iterators.demo;

import secondSet.iterators.FlatteningIterator;

import java.util.Iterator;
import java.util.List;

public class FlatteningIteratorDemo {
    public static void main(String[] args) {
        Iterator<Integer>iter = new FlatteningIterator<>(
                List.of(42, 5).iterator(),
                List.of(-4).iterator(),
                List.of(999, 998, 997).iterator()
        );
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }


    }
}
