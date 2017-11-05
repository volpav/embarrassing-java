package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.Reader;

import java.util.*;

public class ContiguousArray implements SolutionRunner {
    public void run(Scanner s) {
        int[] nums = Reader.readArray(s);
        System.out.println(findMaxLength(nums));
    }

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, -1);

        int max = 0, count = 0;

        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);

            if (map.containsKey(count)) {
                max = Math.max(max, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }

        return max;
    }
}
