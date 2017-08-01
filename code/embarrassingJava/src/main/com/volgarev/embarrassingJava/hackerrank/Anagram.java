package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class Anagram implements SolutionRunner {
    public void run(Scanner s) {
        int q = s.nextInt();

        for(int a0 = 0; a0 < q; a0++){
            String str = s.next();
            int result = anagram(str);

            System.out.println(result);
        }
    }

    static int anagram(String s) {
        if (s.length() % 2 != 0) {
            return -1;
        }

        int offset = 'a';
        int[] counts = new int[26];

        for (int i = s.length() - 1; i >= s.length() / 2; i--) {
            counts[s.charAt(i) - offset]++;
        }

        for (int i = s.length() / 2 - 1; i >= 0; i--) {
            counts[s.charAt(i) - offset]--;
        }

        int notEnoughCharacters = 0,
            extraCharacters = 0;

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] < 0) {
                extraCharacters += Math.abs(counts[i]);
            } else if (counts[i] > 0) {
                notEnoughCharacters += counts[i];
            }
        }

        return Math.max(notEnoughCharacters, extraCharacters);
    }
}
