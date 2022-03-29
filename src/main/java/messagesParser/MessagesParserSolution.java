package messagesParser;

import utilities.FileHandler;

import java.util.Arrays;


public class MessagesParserSolution {
    private int wordPointer = 0;
    private String inputFilePath;
    private int lengthLimit;
    private String[] words;
    private String[] wordsAndLimitArray;

    public MessagesParserSolution(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public void parseMessage() {
       setWords();
       setLengthLimit();
       for (int i = 0; i < words.length-1; i++) {
            if (words[i].length() > lengthLimit) {
                throw new MessagesParserException("Error: One of the words is longer than lengthLimit");
            }
        }
        printLines();
    }

    private void setWords() {
        wordsAndLimitArray = FileHandler.convertToOneString(inputFilePath)
                .toString()
                .split(" ");
        words = Arrays.copyOf(wordsAndLimitArray, wordsAndLimitArray.length-1);
    }

    private void setLengthLimit() {
        lengthLimit = Integer.parseInt(wordsAndLimitArray[wordsAndLimitArray.length-1]);
    }

    private void printLines() {
        StringBuilder line = new StringBuilder();
        while (wordPointer <= words.length) {
            if (line.length() > lengthLimit) {
                System.out.println(line);
                printLines();
                return;
            } else {
                if (wordPointer == words.length) {
                    System.out.println(line);
                    return;
                }
                if (line.length() + words[wordPointer].length() > lengthLimit) {
                    System.out.println(line);
                    printLines();
                    return;
                } else {
                    line.append(words[wordPointer] + " ");
                    wordPointer++;
                }
            }
        }
    }
}
