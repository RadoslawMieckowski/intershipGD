package secondSet.iterators.demo;

import java.time.LocalDate;
import java.util.Iterator;

public class BusinessDaysIteratorDemo {
    public static void main(String[] args) {
        Iterator<LocalDate> iter = new secondSet.iterators.BusinessDaysIterator<>(
                LocalDate.of(2022, 1, 1)
        );
        for (int i = 1; i <= 31; i++) {
            System.out.println(iter.next());
        }
    }
}
