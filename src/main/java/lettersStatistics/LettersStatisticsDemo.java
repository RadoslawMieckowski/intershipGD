package lettersStatistics;

public class LettersStatisticsDemo {

    public static void main(String[] args) {
        LettersStatisticsSolution lss = new LettersStatisticsSolution("src/main/resources/data/input.txt");
        lss.printTopTenLetters();
    }
}
