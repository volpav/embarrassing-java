package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.Reader;

import java.util.*;

public class TwoSum implements SolutionRunner {
    public void run(Scanner s) {
        int[] nums = Reader.readArray(s);
        int target = s.nextInt();

        int[] sum = twoSum(nums, target);

        System.out.println(String.format("%d %d", sum[0], sum[1]));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> suffix = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            int rem = target - n;

            if (suffix.containsKey(rem)) {
                return new int[] { i, suffix.get(rem) };
            }

            suffix.put(n, i);
        }

        return null;
    }
}
