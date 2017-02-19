package com.volgarev.embarrassingJava.misc;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class TwoNumberSum implements SolutionRunner {
    public void run(Scanner s) {
        int n = s.nextInt();
        int[] nums = new int[n];

        while ((n--) > 0) {
            nums[nums.length - (n + 1)] = s.nextInt();
        }

        int sum = s.nextInt();

        System.out.println(SumsUp(nums, sum) ? "Contains sum!" : "Doesn't contain sum...");
    }

    /**
     * Checks whether the given array of numbers contains two elements that sum-up to a given value.
     *    
     * @param numbers Input array.
     * @param sum Target sum.
     * @return A boolean value indicating whether there are two numbers in the input array that make-up the target sum.
     */
    private static boolean SumsUp(int[] numbers, int sum) {
        Set<Integer> prefixes = new HashSet<>();

        for (int n : numbers) {
            if (n < sum) {
                int rem = sum - n;

                if (prefixes.contains(rem)) {
                    return true;
                } else {
                    prefixes.add(n);
                }
            }
        }

        return false;
    }
}