package com.volodymyrbaisa.quizapp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Bios on 1/24/2018.
 */

public class IOUtils {
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    private IOUtils() {throw new IllegalAccessError("Utility class");}

    public static String toString(InputStream input) throws IOException {
        StringWriter sw = new StringWriter();
        copy(input, sw);
        return sw.toString();
    }

    private static int copy(InputStream input, StringWriter sw) throws IOException {
        InputStreamReader in = new InputStreamReader(input);

        long count = copyLarge(in, sw);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    private static long copyLarge(Reader input, Writer output) throws IOException {
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        long count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
}
