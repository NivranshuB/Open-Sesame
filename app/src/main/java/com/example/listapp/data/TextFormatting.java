package com.example.listapp.data;

import java.util.List;

/**
 * A helper class that has static responsibilities of formatting the different strings used in our
 * UI and adapter classes.
 */
public class TextFormatting {

    /**
     * Takes a list of strings and returns a single string with spacing between words
     */
    public static String mergeStringList(List<String> stringList) {
            String mergedString = "";

            for (String s : stringList) {
                mergedString += s + " ";
            }

            return mergedString;
    }

    /**
     * Takes a float price value and returns a 2 decimal point string which begins with substring
     * 'NZ$'
     */
    public static String formatPrice(float price) {
        return "NZ$" + String.format("%.2f", price);
    }

    /**
     * Takes a string of ASCII characters and returns a string where the first character of each
     * word is uppercase while every following character is lowercase
     */
    public static String capitaliseWord(String str) {
        str = str.toLowerCase();
        String words[] = str.split("\\s");
        String capitalizeWord = "";
        for (String w : words) {
            String first = w.substring(0, 1);
            String afterfirst = w.substring(1);
            capitalizeWord += first.toUpperCase() + afterfirst + " ";
        }
        return capitalizeWord.trim();
    }

    /**
     * Takes a list of Long type and return a dimension string in the following format:
     * 'height x width x depth (mm)'
     */
    public static String formatDimensions(List<Long> dimensions) {
        return dimensions.get(0) + " x " + dimensions.get(1) + " x " +
                dimensions.get(2) + " (mm)";
    }
}
