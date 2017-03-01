package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.Reader;
import com.volgarev.embarrassingJava.util.Writer;

import java.util.Scanner;

public class RemoveDuplicatesFromSortedArray2 implements SolutionRunner {
    public void run(Scanner s) {
        int[] nums = Reader.readArray(s);

        int newLength = removeDuplicates(nums);

        System.out.println(newLength);
        Writer.writeIntArray(nums, newLength);
    }

    public int removeDuplicates(int[] nums) {
        int ret = nums.length,
                current = 0,
                unique = 0;

        boolean skip = true;

        if (nums.length > 1) {
            ret = 1;
            current = 1;

            while (current < nums.length) {
                if (skip && nums[current] == nums[unique]) {
                    ret++;
                    unique++;
                    current++;
                    skip = false;
                }

                while (current < nums.length && nums[current] == nums[unique]) {
                    current++;
                }

                if (current < nums.length) {
                    nums[++unique] = nums[current];
                    ret++;

                    skip = true;
                    current++;
                }
            }
        }

        return ret;
    }
}
