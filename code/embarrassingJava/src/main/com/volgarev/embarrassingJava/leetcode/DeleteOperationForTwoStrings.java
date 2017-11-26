package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class DeleteOperationForTwoStrings implements SolutionRunner {
    public void run(Scanner s) {
        String word1 = s.next();
        String word2 = s.next();

        System.out.print(minDistance(word1, word2));
    }

    public int minDistance(String word1, String word2) {
        int l = longestCommonSubsequence(word1.toCharArray(), word2.toCharArray());

        int rem1 = word1.length() - l;
        int rem2 = word2.length() - l;

        return (rem1 < 0 ? 0 : rem1) + (rem2 < 0 ? 0 : rem2);
    }

    private int longestCommonSubsequence(char[] m, char[] n) {
        int[][] d = new int[m.length + 1][n.length + 1];

        for (int j = 0; j < n.length; j++) {
            for (int i = 0; i < m.length; i++) {
                int d_i = i + 1;
                int d_j = j + 1;

                if (m[i] == n[j]) {
                    d[d_i][d_j] = d[d_i - 1][d_j - 1] + 1;
                } else {
                    d[d_i][d_j] = Math.max(d[d_i - 1][d_j], d[d_i][d_j - 1]);
                }
            }
        }

        return d[m.length][n.length];
    }
}
