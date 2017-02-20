package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class MissingNumber implements SolutionRunner {
    public void run(Scanner s) {
        int n = s.nextInt();

        int i = 0;
        int[] nums = new int[n];

        while ((n--) > 0) {
            nums[i++] = s.nextInt();
        }

        System.out.println(missingNumber(nums));
    }

    public int missingNumber(int[] nums) {
        int n = nums.length,
            perfectSum = ((n + 1) * n) / 2;

        int actualSum = 0;

        for (int i : nums) {
            actualSum += i;
        }

        return perfectSum - actualSum;
    }
}
