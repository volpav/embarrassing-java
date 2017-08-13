package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class TwoStrings implements SolutionRunner {
    static boolean twoStrings(String s1, String s2) {
        boolean[] exists = new boolean[256];

        for (int i = 0; i < s1.length(); i++) {
            exists[s1.charAt(i) - 'a'] = true;
        }

        for (int i = 0; i < s2.length(); i++) {
            if (exists[s2.charAt(i) - 'a']) {
                return true;
            }
        }

        return false;
    }

    public void run(Scanner s) {
        int q = s.nextInt();

        for (int a0 = 0; a0 < q; a0++) {
            String s1 = s.next();
            String s2 = s.next();

            boolean result = twoStrings(s1, s2);
            System.out.println(result ? "YES" : "NO");
        }
    }
}
