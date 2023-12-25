package util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertRomanToDecimal {
    private static final Map<String, Integer> map = new HashMap<>();

    static {
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
    }

    public static int convert(String roman) {
        int i = -1;
        int sum = 0;

        Pattern pattern = Pattern.compile(AppConstants.VALID_ROMAN_NUMERAL_REGEX);
        if (!pattern.matcher(roman).matches()) {
            return -1;
        }
        while (++i < roman.length()) {
            if (i + 1 <= roman.length() - 1) {
                String n = roman.substring(i, i + 2);
                if (map.containsKey(n)) {
                    sum += map.get(n);
                    i++;
                    continue;
                }
            }
            sum += map.get(String.valueOf(roman.charAt(i)));
        }
        return sum;
    }
}