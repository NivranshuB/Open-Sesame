package com.example.listapp.data;

import java.util.List;

public class TextFormatting {

    public static String mergeStringList(List<String> stringList) {
            String mergedString = "";

            for (String s : stringList) {
                mergedString += s + " ";
            }

            return mergedString;
    }

    public static String formatPrice(float price) {
        return "NZ$" + String.format("%.2f", price);
    }

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

    public static String formatDimensions(List<Long> dimensions) {
        return dimensions.get(0) + " x " + dimensions.get(1) + " x " +
                dimensions.get(2) + " (mm)";
    }
}
