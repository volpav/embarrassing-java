package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IsomorphicStrings implements SolutionRunner {
    public void run(Scanner s) {
        String s1 = s.next(), s2 = s.next();

        System.out.println(isIsomorphic(s1, s2));
    }

    public boolean isIsomorphic(String s, String t) {
        final char[] ch1 = s != null ? s.toCharArray() : new char[0];
        final char[] ch2 = t != null ? t.toCharArray() : new char[0];

        if (ch1.length != ch2.length) {
            return false;
        }

        final boolean[] mf = new boolean[255];
        final char[] mt = new char[255];
        final char empty = '\0';

        for (int i = 0; i < ch1.length; i++) {
            int x = ch1[i], y = ch2[i];

            if (!mf[x] && mt[y] == empty) {
                mf[x] = true;
                mt[y] = ch1[i];
            } else if (mt[y] != ch1[i]) {
                return false;
            }
        }

        return true;
    }
}
