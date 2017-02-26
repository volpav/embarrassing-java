package com.volgarev.embarrassingJava.util;


public class Writer {
    public static void writeArray(int[] items) {
        writeArray(items, items.length);
    }

    public static void writeArray(int[] items, int size) {
        System.out.print("[ ");

        for (int i = 0; i < size; i++) {
            System.out.print(items[i]);

            if (i < (size - 1)) {
                System.out.print(",");
            }

            System.out.print(" ");
        }

        System.out.println("]");
    }
}
