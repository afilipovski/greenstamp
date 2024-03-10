package com.example.util;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    /**
     * 
     * @param str String to parse as integer.
     * @return If the string is an integer format, returns it as an integer. Otherwise, returns null.
     */
    public static Integer tryParseInt(String str) {
        try {
            return Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 
     * @param String to split.
     * @param Regular expression.
     * @return If string is null, returns null. If regex is null, returns a list containing only the string.
     *         Otherwise, returns a list of strings after splitting.
     */
    public static List<String> trySplitIntoList(String str, String regex) {
        try {
            return Arrays.asList(str.split(regex));
        }
        catch (NullPointerException e) {
            if (str == null)
                return null;
            return List.of(str);
        }
    }

    /**
     * 
     * @param arg0 A sequence of chars between each of the members of the iterable.
     * @param arg1 An Iterable of char sequences to be connected.
     * @return If sequence is null, returns null. Otherwise, returns the join as a single string.
     */
    public static String tryJoin(CharSequence arg0, Iterable<? extends CharSequence> arg1) {
        try {
            return String.join(arg0, arg1);
        }
        catch (NullPointerException e) {
            return null;
        }
    }
}
