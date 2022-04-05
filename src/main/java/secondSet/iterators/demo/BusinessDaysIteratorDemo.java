package secondSet.iterators.demo;

import java.time.LocalDate;
import java.util.Iterator;

public class BusinessDaysIteratorDemo {
    public static void main(String[] args) {
        Iterator<LocalDate> iter = new secondSet.iterators.BusinessDaysIterator<>(
                LocalDate.of(2022, 1, 1)
        );
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
    }
}
