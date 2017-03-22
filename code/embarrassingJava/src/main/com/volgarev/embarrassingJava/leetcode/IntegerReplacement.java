package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class IntegerReplacement implements SolutionRunner {
    public void run(Scanner s) {
        int n = s.nextInt();
        System.out.println(integerReplacement(n));
    }

    public int integerReplacement(int n) {
        int ret = 0;

        while (n != 1) {
            if ((n & 1) == 0) {
                n >>>= 1;
            } else if (n == 3 || ((n >>> 1) & 1) == 0) {
                n--;
            } else {
                n++;
            }

            ret++;
        }

        return ret;
    }
}

