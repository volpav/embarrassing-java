package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.Reader;
import com.volgarev.embarrassingJava.util.Writer;

import java.util.*;

public class RelativeRanks implements SolutionRunner {
    public void run(Scanner s) {
        int[] nums = Reader.readArray(s);

        String[] ranks = findRelativeRanks(nums);

        Writer.writeArray(ranks);
    }

    public String[] findRelativeRanks(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new String[0];
        }

        int n = nums.length;
        Integer[] numsSorted = new Integer[n];

        for (int i = 0; i < n; i++) {
            numsSorted[i] = nums[i];
        }

        Arrays.sort(numsSorted, new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                return y - x;
            }
        });

        Map<Integer, Integer> pos = new HashMap<>();

        for (int i = 0; i < n; i++) {
            pos.put(numsSorted[i], i);
        }

        String[] ranks = new String[] {
            "Gold Medal",
            "Silver Medal",
            "Bronze Medal"
        };

        String[] output = new String[n];

        for (int i = 0; i < n; i++) {
            int p = pos.get(nums[i]);

            if (p < ranks.length) {
                output[i] = ranks[p];
            } else {
                output[i] = Integer.toString(p + 1);
            }
        }

        return output;
    }
}
