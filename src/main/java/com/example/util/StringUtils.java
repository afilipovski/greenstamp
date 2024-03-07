package com.example.util;

public class StringUtils {
    public static Integer tryParseInt(String str) {
        try {
            return Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    public static String[] trySplit(String str, String delim) {
        try {
            return str.split(delim);
        }
        catch (NullPointerException e) {
            return new String[0];
        }
    }
}
