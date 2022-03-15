import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MessagesParser {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/messagesParser.txt");
        Scanner in = new Scanner(file);
        String[] line = in.nextLine().split(" ");
        int limitLength = Integer.parseInt(in.nextLine());
        System.out.println(Arrays.toString(line));
        System.out.println(limitLength);
        in.close();
    }
}
