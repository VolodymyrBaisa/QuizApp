package com.volodymyrbaisa.quizapp.utils;

/**
 * Created by Bios on 1/22/2018.
 */

public class PreconditionsUtils {
    private PreconditionsUtils() {throw new IllegalAccessError("Utility class");}

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
