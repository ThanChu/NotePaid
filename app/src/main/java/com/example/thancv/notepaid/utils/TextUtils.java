package com.example.thancv.notepaid.utils;

/**
 * Created by ThanCV on 8/23/2017.
 */

public class TextUtils {
    private TextUtils() {
    }

    public static String formatText(long number) {
        String s = null;
        try {
            s = String.format("%,d", number);
        } catch (NumberFormatException e) {
        }
        return s;
    }
}
