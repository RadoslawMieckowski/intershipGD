package messagesParser;

import utilities.FileHandler;



public class MessagesParserSolution {
    private int wordPointer = 0;
    private String inputFilePath;
    private int lengthLimit;
    private String[] words;

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
        words = FileHandler.convertToOneString(inputFilePath)
                .toString()
                .split(" ");
    }

    private void setLengthLimit() {
        lengthLimit = Integer.parseInt(words[words.length-1]);
    }

    private void printLines() {
        StringBuilder line = new StringBuilder();
        while (wordPointer < words.length) {
            if (line.length() > lengthLimit) {
                System.out.println(line);
                printLines();
                return;
            } else {
                if (wordPointer == words.length - 1) {
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
