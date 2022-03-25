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

        Map<String, Double> map = new LinkedHashMap<>();
        Arrays.stream(arrayOfStrings).forEach(x -> map.put(x, Double.parseDouble(x)));
        StringBuilder strbdr = new StringBuilder();
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(x -> strbdr.append(x.getKey() + ", "));

        System.out.println(strbdr.substring(0, strbdr.length()-2));
    }
}
