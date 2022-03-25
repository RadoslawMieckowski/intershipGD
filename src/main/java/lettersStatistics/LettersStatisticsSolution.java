package lettersStatistics;

import utilities.FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LettersStatisticsSolution {
   private String inputFilePath;
    public LettersStatisticsSolution(String inputFilePath) {
       this.inputFilePath = inputFilePath;
   }
    public void printTopTenLetters() {

       String line = FileHandler.convertToOneString(inputFilePath);

        Map<Character, Long> mapOfDistinctLetters = line.chars().mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

       LinkedHashMap<Character, Long> sortedMapOfLetters = new LinkedHashMap<>();

       mapOfDistinctLetters.entrySet()
               .stream()
               .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
               .forEachOrdered(entry -> sortedMapOfLetters.put(entry.getKey(), entry.getValue()));

       int limit = 0;
       for (Map.Entry<Character, Long> entry : sortedMapOfLetters.entrySet()) {
           System.out.println(entry.getKey() + ": " + entry.getValue());
           limit++;
           if (limit == 10) break;
       }
   }
}
