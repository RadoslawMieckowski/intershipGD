package secondSet.iterators.demo;

import secondSet.iterators.ZippingIterator;

import java.util.List;

public class ZippingIteratorDemo {
    public static void main(String[] args) {
        ZippingIterator<String,Integer> personIterator = new ZippingIterator<>(
                List.of("John", "Jane", "Jack", "Dennis").iterator(),
                List.of(24, 25, 27, 12).iterator()
        );
        while (personIterator.hasNext()) {
            System.out.println(personIterator.next());
        }
    }
}
