import java.io.File;
import java.io.IOException;
import java.util.*;

public class LettersStatistics {
    public static void main(String[] args) throws IOException {
        File file = new File("src/input.txt");
        Scanner in = new Scanner(file);
        String[] line = in.nextLine().toLowerCase().split("");
        List<String> listOfLetters = Arrays.asList(line);
        //System.out.println(list);
        HashSet<String> distinctLetters = new HashSet<>(listOfLetters);
        System.out.println("Before: " + distinctLetters);
        distinctLetters.removeIf(letter->
                        letter.equals(" ")||
                        letter.equals(",")||
                        letter.equals(".")||
                        letter.equals("?")||
                        letter.equals("!")
                );
        System.out.println("After: " + distinctLetters);
    }
}
