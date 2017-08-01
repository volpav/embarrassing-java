package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class PalindromeIndex implements SolutionRunner {
    public void run(Scanner s) {
        int q = s.nextInt();

        for (int a0 = 0; a0 < q; a0++) {
            String str = s.next();
            int result = palindromeIndex(str);

            System.out.println(result);
        }
    }

    private static int palindromeIndex(String s) {
        int[] mismatch = getPalindromeMismatch(s);

        if (mismatch.length > 0) {
            String modified = new StringBuffer(s).deleteCharAt(mismatch[0]).toString();
            return getPalindromeMismatch(modified).length == 0 ? mismatch[0] : mismatch[1];
        }

        return -1;
    }

    private static int[] getPalindromeMismatch(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return new int[] { i, s.length() - i - 1 };
            }
        }

        return new int[] {};
    }
}
