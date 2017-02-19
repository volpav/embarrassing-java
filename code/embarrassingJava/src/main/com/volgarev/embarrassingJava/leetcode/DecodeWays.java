package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class DecodeWays implements SolutionRunner {
    public void run(Scanner s) {
        String str = s.nextLine();

        System.out.println(numDecodings(str));
    }

    public int numDecodings(String s) {
        int ret = 0;

        if (s != null && s.length() > 0) {
            int n = s.length();
            int[] trace = new int[n + 1];

            trace[n] = 1;
            trace[n - 1] = s.charAt(n - 1) == '0' ? 0 : 1;

            for (int i = n - 2; i >= 0; i--) {
                if (s.charAt(i) == '0') {
                    continue;
                }

                trace[i] = Integer.parseInt(s.substring(i, i + 2)) <= 26 ? 
                    trace[i + 1] + trace[i + 2] : 
                    trace[i + 1];
            }

            ret = trace[0];
        }

        return ret;
    }
}