package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class HackerrankInAString implements SolutionRunner {
    private static final char[] search = new char[] { 'h', 'a', 'c', 'k', 'e', 'r', 'r', 'a', 'n', 'k' };

    public void run(Scanner s) {
        int q = s.nextInt();

        while (q-- > 0) {
            System.out.println(containsHackerrank(s.next()) ? "YES" : "NO");
        }
    }

    private boolean containsHackerrank(String s) {
        int offset = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == search[offset]) {
                offset++;

                if (offset >= search.length) {
                    return true;
                }
            }
        }

        return false;
    }
}
