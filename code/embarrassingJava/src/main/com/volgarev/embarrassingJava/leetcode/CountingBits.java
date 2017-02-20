package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class CountingBits implements SolutionRunner {
    public void run(Scanner s) {
        int num = s.nextInt();

        int[] bits = countBits(num);

        System.out.print("[ ");

        for (int b : bits) {
            System.out.print(b + " ");
        }

        System.out.println("]");
    }

    public int[] countBits(int num) {
        int[] ret = new int[num + 1];

        for (int i = 1; i <= num; i++) {
            ret[i] = numberOfSetBits(i);
        }

        return ret;
    }

    private int numberOfSetBits(int i)
    {
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        return (((i + (i >>> 4)) & 0x0F0F0F0F) * 0x01010101) >>> 24;
    }
}
