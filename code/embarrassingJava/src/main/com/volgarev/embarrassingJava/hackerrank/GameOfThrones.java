package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class GameOfThrones implements SolutionRunner {
    public void run(Scanner s) {
        String str = s.next();
        boolean result = gameOfThrones(str);

        System.out.println(result ? "YES" : "NO");
    }

    private static boolean gameOfThrones(String s) {
        int offset = 'a';
        boolean[] appear = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - offset;
            appear[index] = !appear[index];
        }

        int cnt = 0;
        boolean isOddLength = s.length() % 2 != 0;

        for (int i = 0; i < appear.length; i++) {
            if (appear[i]) {
                cnt++;

                if (isOddLength) {
                    if (cnt > 1) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}
