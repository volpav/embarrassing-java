package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;
import java.util.Stack;

public class ReverseString2 implements SolutionRunner {
    public void run(Scanner s) {
        System.out.println(reverseStr(s.next(), s.nextInt()));
    }

    public String reverseStr(String s, int k) {
        Stack<Character> reversed = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (reversed.size() < k) {
                reversed.push(s.charAt(i));
            } else {
                while (reversed.size() > 0) {
                    sb.append(reversed.pop());
                }

                int n = 0;

                while (i < s.length() && n++ < k) {
                    sb.append(s.charAt(i++));
                }

                i--;
            }
        }

        while (reversed.size() > 0) {
            sb.append(reversed.pop());
        }

        return sb.toString();
    }

}
