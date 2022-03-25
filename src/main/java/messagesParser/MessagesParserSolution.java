package messagesParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MessagesParserSolution {
    private int wordPointer = 0;
    private String[] words;
    private int lengthLimit;
    private String inputFilePath;

    public MessagesParserSolution(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public void parseMessage() {
        File file = new File(inputFilePath);
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            in.close();
            e.printStackTrace();
        }
        words = in.nextLine().split(" ");
        lengthLimit = Integer.parseInt(in.nextLine());

        for (String x: words) {
            if (x.length() > lengthLimit) {
                throw new MessagesParserException("Error: One of the words is longer than lengthLimit");
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
