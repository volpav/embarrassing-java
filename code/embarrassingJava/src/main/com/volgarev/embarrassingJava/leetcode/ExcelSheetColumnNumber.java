package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class ExcelSheetColumnNumber implements SolutionRunner {
    public void run(Scanner s) {
        System.out.println(titleToNumber(s.next()));
    }

    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int m = 0, ret = 0;
        char[] chars = s.toCharArray();

        for (int i = chars.length - 1; i >= 0; i--) {
            int v = (((int)chars[i]) - ((int)'A')) + 1;
            ret += (v * Math.pow(26, m++));
        }

        return ret;
    }
}
