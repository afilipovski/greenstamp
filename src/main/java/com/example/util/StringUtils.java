package com.example.util;

import java.util.List;

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

    public static String tryJoin(String delim, List<String> list) {
        try {
            return String.join(delim, list);
        }
        catch (NullPointerException e) {
            return null;
        }
    }
}
