package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.Stack;

public class LargestRectangleInHistogram implements SolutionRunner {
    public void run(Scanner s) {
        char[] chars = s.nextLine().toCharArray();

        int[] heights = IntStream.range(0, chars.length)
            .map(i -> Character.getNumericValue(chars[i]))
            .toArray();

        System.out.println(largestRectangleArea(heights));
    }

    public int largestRectangleArea(int[] heights) {
        int len = heights.length, ret = 0;
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i <= len; i++) {
            int h = i == len ? 0 : heights[i];

            if (s.isEmpty() || h >= heights[s.peek()]) {
                s.push(i);
            } else {
                int top = s.pop();

                ret = Math.max(ret, heights[top] * (s.isEmpty() ? i : i - 1 - s.peek()));
                
                i--;
            }
        }

        return ret;
    }
}