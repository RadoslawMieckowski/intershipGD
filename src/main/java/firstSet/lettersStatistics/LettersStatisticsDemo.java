package firstSet.lettersStatistics;

import java.util.Map;

public class LettersStatisticsDemo {

    public static void main(String[] args) {
        LettersStatisticsSolution lettersStatisticsSolution1 = new LettersStatisticsSolution("src/main/resources/data/input.txt");
        String line = lettersStatisticsSolution1.readLineFromFile();
        Map<Character, Long> mapOfDistinctLetters = lettersStatisticsSolution1.createMapOfDistinctLetters(line);
        Map<Character, Long> sortedMapOfLetters = lettersStatisticsSolution1.createSortedMapOfLetters(mapOfDistinctLetters);
        lettersStatisticsSolution1.printFirstTenEntries(sortedMapOfLetters);
    }
}
