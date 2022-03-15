import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MessagesParser {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/messagesParser.txt");
        Scanner in = new Scanner(file);
        String[] words = in.nextLine().split(" ");
        int limitLength = Integer.parseInt(in.nextLine());
//        System.out.println(Arrays.toString(words));
//        System.out.println(limitLength);
        int pointer = 0;
        while (pointer < words.length) {
            String line = "";
//            System.out.println("line: " + line);
            int counter = 0;
//            System.out.println("counter: " + counter);
//            System.out.println("pointer: " + pointer);
//            System.out.println("======wewn petla=======");
            while (counter < limitLength) {
                counter = words[pointer].length();
//                System.out.println("counter: " + counter);
                if (counter <= words.length) {
                    counter++;
//                    System.out.println("counter: " + counter);
                    line = line.concat(words[pointer]).concat(" ");
//                    System.out.println(line);
                    pointer++;
                    if (pointer == words.length) break;
//                    System.out.println("pointer: " + pointer);
                    counter += words[pointer].length() + 1;
//                    System.out.println("counter: " + counter);
                    line = line.concat(words[pointer]).concat(" ");
//                    System.out.println(line);
                }
//                System.out.println("pointer: " + pointer);
//                System.out.println(line);
//                System.out.println("----------------------");
                if (counter < limitLength - 1) {
                    pointer++;
                }
            }
            System.out.println(line);
            //System.out.println("counter: " + counter);
            pointer++;
            //System.out.println("pointer: " + pointer);

            //System.out.println("======================");
        }
        in.close();
    }
}
