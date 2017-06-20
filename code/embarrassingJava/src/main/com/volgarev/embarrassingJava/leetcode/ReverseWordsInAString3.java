package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ReverseWordsInAString3 implements SolutionRunner {
    public void run(Scanner s) {
        String str = s.nextLine();

        System.out.println(reverseWords(str));
    }

    public String reverseWords(String s) {
        Queue<Stack<Character>> contents = new LinkedList<Stack<Character>>();

        Stack<Character> cur = null;

        for (Character ch : s.toCharArray()) {
            if (!Character.isWhitespace(ch)) {
                if (cur == null) {
                    cur = new Stack<>();
                    contents.add(cur);
                }

                cur.push(ch);
            } else {
                cur = null;
            }
        }

        StringBuilder sb = new StringBuilder();

        while (contents.size() > 0) {
            Stack<Character> item = contents.remove();

            if (sb.length() > 0) {
                sb.append(" ");
            }

            while (item.size() > 0) {
                sb.append(item.pop());
            }
        }

        return sb.toString();
    }
}
