package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class TheLoveLetterMystery implements SolutionRunner {
    public void run(Scanner s) {
        int q = s.nextInt();

        for (int a0 = 0; a0 < q; a0++) {
            String str = s.next();
            int result = theLoveLetterMystery(str);

            System.out.println(result);
        }
    }

    private static int theLoveLetterMystery(String s) {
        int cnt = 0;

        for (int i = 0; i < s.length() / 2; i++) {
            char left = s.charAt(i);
            char right = s.charAt(s.length() - i - 1);

            if (left != right) {
                cnt += (Math.abs(left - right));
            }
        }

        return cnt;
    }
}
