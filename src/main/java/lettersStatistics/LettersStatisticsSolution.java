package lettersStatistics;

import utilities.FileHandler;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LettersStatisticsSolution {
   private String inputFilePath;
    public LettersStatisticsSolution(String inputFilePath) {
       this.inputFilePath = inputFilePath;
   }

    String readLineFromFile() {
        StringBuilder lineBuilder = FileHandler.convertToOneString(inputFilePath);
        return FileHandler.removeNonLetterAndMakeSmall(lineBuilder);
   }

     Map<Character, Long> createMapOfDistinctLetters(String line) {
        return line.chars().mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

     Map<Character, Long> createSortedMapOfLetters(Map<Character, Long> mapOfDistinctLetters) {
         return mapOfDistinctLetters.entrySet()
                 .stream()
                 .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                 .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
     }

     void printFirstTenEntries(Map<Character, Long> map) {
        map.entrySet()
                .stream()
                .limit(10)
                .forEach(entry ->System.out.println(entry.getKey() + ": " + entry.getValue() ));
    }
}
