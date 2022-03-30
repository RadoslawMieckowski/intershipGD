package secondSet.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class CSVReader {
    private CSVReader(){}

    public static List<String> readCSVFile(String filePath) {
        List<String> entries = null;
        try {
            entries = Files.lines(Paths.get(filePath))
                    .skip(1)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(entries);
        return entries;
    }
}
