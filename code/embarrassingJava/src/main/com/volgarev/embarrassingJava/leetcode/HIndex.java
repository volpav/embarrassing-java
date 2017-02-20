package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.Reader;

import java.util.Arrays;
import java.util.Scanner;

public class HIndex implements SolutionRunner {
    public void run(Scanner s) {
        int[] citations = Reader.readArray(s);

        System.out.println(hIndex(citations));
    }

    public int hIndex(int[] citations) {
        Arrays.sort(citations);

        for (int i = citations.length; i >= 1; i--) {
            int atLeastStart = citations.length - i;

            if (citations[atLeastStart] >= i) {
                return i;
            }
        }

        return 0;
    }
}
