import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class LettersStatistics {
    public static void main(String[] args) {
        File file = new File("src/input.txt");
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HashSet<String> distinctLetters = new HashSet<>();
        List<String> listOfLetters = null;
        while(in.hasNextLine()) {
            String[] line = in.nextLine().toLowerCase().split("");
            listOfLetters= Arrays.asList(line);
            distinctLetters.addAll(listOfLetters);
        }
        //System.out.println("Before: " + distinctLetters);

        distinctLetters.removeIf(letter->
                        letter.equals(" ")||
                        letter.equals(",")||
                        letter.equals(".")||
                        letter.equals("?")||
                        letter.equals("!")
                );
        //System.out.println("After: " + distinctLetters);
        TreeMap<String, Integer> mapOfDistinctLetters = new TreeMap<>();
        int counter = 0;
        for (String searchedLetter: distinctLetters) {
            for (String letter: listOfLetters) {
                if (searchedLetter.equals(letter)) {
                    counter++;
                }
            }
            mapOfDistinctLetters.put(searchedLetter, counter);
            counter = 0;
        }
        //System.out.println("unsorted map of letters: " + mapOfDistinctLetters);

        LinkedHashMap<String, Integer> sortedMapOfLetters = new LinkedHashMap<>();

        mapOfDistinctLetters.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(entry -> sortedMapOfLetters.put(entry.getKey(), entry.getValue()));

        int limit = 0;
        for (Map.Entry<String, Integer> entry: sortedMapOfLetters.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            limit++;
            if (limit == 10) break;
        }

        in.close();
    }
}
