import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LettersStatistics {
    public static void main(String[] args) throws IOException {
        File file = new File("src/input.txt");
        Scanner in = new Scanner(file);
        String line = in.nextLine();
        System.out.println(line);
    }
}
