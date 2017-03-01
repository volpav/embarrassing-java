package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.Reader;
import com.volgarev.embarrassingJava.util.Writer;

import java.util.Scanner;

public class RemoveDuplicatesFromSortedArray implements SolutionRunner {
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

        if (nums.length > 1) {
            ret = 1;
            current = 1;

            while (current < nums.length) {
                while (current < nums.length && nums[current] == nums[unique]) {
                    current++;
                }

                if (current < nums.length) {
                    nums[++unique] = nums[current];
                    ret++;
                }

            }
        }

        return ret;
    }
}
