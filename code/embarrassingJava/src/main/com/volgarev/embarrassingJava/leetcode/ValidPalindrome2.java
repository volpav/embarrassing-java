package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class ValidPalindrome2 implements SolutionRunner {
    public void run(Scanner s) {
        System.out.println(validPalindrome(s.next()));
    }

    public boolean validPalindrome(String s) {
        if (s == null || s.isEmpty()) return false;
        if (s.length() == 1) return true;

        char[] chars = s.toCharArray();
        int nonMatchingLeft = isPalindrome(chars, 0, chars.length - 1);

        if (nonMatchingLeft < 0) return true;

        return isPalindrome(chars, nonMatchingLeft + 1, chars.length - nonMatchingLeft - 1) < 0 ||
                isPalindrome(chars, nonMatchingLeft, chars.length - nonMatchingLeft - 2) < 0;
    }

    private int isPalindrome(char[] chars, int left, int right) {
        while (left <= right) {
            if (chars[left] != chars[right]) {
                return left;
            }

            left++;
            right--;
        }

        return -1;
    }
}
