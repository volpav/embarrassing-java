package com.volgarev.embarrassingJava.util;

import java.util.Scanner;

public class Reader {
    public static int[] readArray(Scanner s) {
        int n = s.nextInt();

        int index = 0;
        int[] ret = new int[n];

        while ((n--) > 0) {
            ret[index++] = s.nextInt();
        }

        return ret;
    }
}
