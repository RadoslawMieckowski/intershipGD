package numberFormat;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class NumberFormatSolution {

    public void printSortedStrings() {
        String[] arrayOfStrings = new String[10];
        arrayOfStrings[0] = "3";
        arrayOfStrings[1] = "0.5";
        arrayOfStrings[2] = "0.1";
        arrayOfStrings[3] = "35";
        arrayOfStrings[4] = "50.44";
        arrayOfStrings[5] = "000.000";
        arrayOfStrings[6] = "1.15022";
        arrayOfStrings[7] = ".5";
        arrayOfStrings[8] = ".1";
        arrayOfStrings[9] = "-12";

        LinkedHashMap<String, Double> unsortedMap = new LinkedHashMap<>();
        Arrays.stream(arrayOfStrings).forEach(x -> unsortedMap.put(x, Double.parseDouble(x)));

        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();
        unsortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        StringBuilder strbdr = new StringBuilder();
        sortedMap.entrySet().forEach(x -> strbdr.append(x.getKey() + ", "));
        System.out.println(strbdr.substring(0, strbdr.length()-2));

    }
}
