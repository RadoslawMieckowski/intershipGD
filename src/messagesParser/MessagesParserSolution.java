package messagesParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MessagesParserSolution {
    private int wordPointer = 0;
    private String[] words;
    private int lengthLimit;

    public void parseMessage() throws FileNotFoundException {
        File file = new File("src/data/messagesParser.txt");
        Scanner in = new Scanner(file);
        words = in.nextLine().split(" ");
        lengthLimit = Integer.parseInt(in.nextLine());

        for (String x: words) {
            if (x.length() > lengthLimit) {
                throw new MessagesParserException("Error");
            }
        }

        printLines();

        in.close();
    }
    private void printLines() {
        String line = "";
        while (wordPointer <= words.length) {
            if (line.length() > lengthLimit) {
                System.out.println(line);
                printLines();
                break;
            } else {
                if (wordPointer == words.length) {
                    System.out.println(line);
                    break;
                }
                if (line.length() + words[wordPointer].length() > lengthLimit) {
                    System.out.println(line);
                    printLines();
                    break;
                } else {
                    line = line.concat(words[wordPointer]).concat(" ");
                    wordPointer++;
                }
            }
        }
    }
}
