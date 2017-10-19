package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LongestRepeatingCharacterReplacement implements SolutionRunner {
    public void run(Scanner s) {
        String str = s.next();
        int k = s.nextInt();

        System.out.println(characterReplacement(str, k));
    }

    public int characterReplacement(String s, int k) {
        int[] count = new int[128];
        int max = 0;
        int start = 0;

        for (int end = 0; end < s.length(); end++) {
            max = Math.max(max, ++count[s.charAt(end)]);
            if (max + k <= end - start) {
                count[s.charAt(start++)]--;
            }

        }

        return s.length() - start;
    }
}
