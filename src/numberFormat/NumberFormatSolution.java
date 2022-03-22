package numberFormat;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class NumberFormatSolution {
    public static void main(String[] args) {

        String[] s = new String[10];
        s[0] = "3";
        s[1] = "0.5";
        s[2] = "0.1";
        s[3] = "35";
        s[4] = "50.44";
        s[5] = "000.000";
        s[6] = "1.15022";
        s[7] = ".5";
        s[8] = ".1";
        s[9] = "-12";

        LinkedHashMap<String, Double> unsortedMap = new LinkedHashMap<>();
        Arrays.stream(s).forEach(x -> unsortedMap.put(x, Double.parseDouble(x)));

        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();
        unsortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        StringBuilder strbdr = new StringBuilder();
        sortedMap.entrySet().forEach(x -> strbdr.append(x.getKey() + ", "));
        System.out.println(strbdr.substring(0, strbdr.length()-2));

    }
}
