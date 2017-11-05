package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.Reader;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MinimumSizeSubarraySum implements SolutionRunner {
    public void run(Scanner s) {
        int[] nums = Reader.readArray(s);
        int sum = s.nextInt();

        System.out.println(minSubArrayLen(sum, nums));
    }

    public int minSubArrayLen(int s, int[] nums) {
        int count = 0, left = 0, min = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            count += nums[right];

            if (count >= s) {
                while (left <= right && count >= s) {
                    min = Math.min(min, right - left + 1);
                    count -= nums[left];
                    left++;
                }
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
