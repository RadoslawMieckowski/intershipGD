package lettersStatistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LettersStatisticsSolution {
   public void printTopTenLetters() {
       File file = new File("src/data/input.txt");
       Scanner in = null;
       try {
           in = new Scanner(file);
       } catch (FileNotFoundException e) {
           in.close();
           e.printStackTrace();
       }

       HashSet<String> distinctLetters = new HashSet<>();
       List<String> listOfLetters = null;
       while (in.hasNextLine()) {
           String[] line = in.nextLine().toLowerCase().split("");
           listOfLetters = Arrays.asList(line);
           distinctLetters.addAll(listOfLetters);
       }
       //System.out.println("Before: " + distinctLetters);

       distinctLetters.removeIf(letter ->
               letter.equals(" ") ||
                       letter.equals(",") ||
                       letter.equals(".") ||
                       letter.equals("?") ||
                       letter.equals("!")
       );
       //System.out.println("After: " + distinctLetters);

       TreeMap<String, Long> mapOfDistinctLetters = new TreeMap<>();
       long counter = 0;
       for (String searchedLetter : distinctLetters) {
           for (String letter : listOfLetters) {
               if (searchedLetter.equals(letter)) {
                   counter++;
               }
           }
           mapOfDistinctLetters.put(searchedLetter, counter);
           counter = 0;
       }

//        Map<String, Long> mapOfDistinctLetters = distinctLetters.stream()
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

       //System.out.println("unsorted map of letters: " + mapOfDistinctLetters);

       LinkedHashMap<String, Long> sortedMapOfLetters = new LinkedHashMap<>();

       mapOfDistinctLetters.entrySet()
               .stream()
               .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
               .forEachOrdered(entry -> sortedMapOfLetters.put(entry.getKey(), entry.getValue()));

       int limit = 0;
       for (Map.Entry<String, Long> entry : sortedMapOfLetters.entrySet()) {
           System.out.println(entry.getKey() + ": " + entry.getValue());
           limit++;
           if (limit == 10) break;
       }
   }
}
