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

   protected String readLineFromFile() {
        StringBuilder lineBuilder = FileHandler.convertToOneString(inputFilePath);
        return FileHandler.removeNonLetterAndMakeSmall(lineBuilder);
   }

    protected Map<Character, Long> createMapOfDistinctLetters(String line) {
        return line.chars().mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    protected Map<Character, Long> createSortedMapOfLetters(Map<Character, Long> mapOfDistinctLetters) {
        Map<Character, Long> sortedMapOfLetters = new LinkedHashMap<>();
        mapOfDistinctLetters.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(entry -> sortedMapOfLetters.put(entry.getKey(), entry.getValue()));
        return sortedMapOfLetters;
    }

    protected void printFirstTenEntries(Map<Character, Long> map) {
        int limit = 0;
        for (Map.Entry<Character, Long> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            limit++;
            if (limit == 10) break;
        }
    }
}
