package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.Reader;

import java.util.Scanner;

public class NumberOfLongestIncreasingSubsequence implements SolutionRunner {
    public void run(Scanner s) {
        int[] nums = Reader.readArray(s);

        System.out.println(findNumberOfLIS(nums));
    }

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length,
            res = 0,
            maxLen = 0;

        int[] len =  new int[n],
              cnt = new int[n];

        for (int i = 0; i < n; i++) {
            len[i] = cnt[i] = 1;

            for (int j = 0; j < i ; j++) {
                if (nums[i] > nums[j]) {
                    if (len[i] == len[j] + 1) {
                        cnt[i] += cnt[j];
                    }

                    if (len[i] < len[j] + 1) {
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j];
                    }
                }
            }
            if (maxLen == len[i]) {
                res += cnt[i];
            }

            if (maxLen < len[i]) {
                maxLen = len[i];
                res = cnt[i];
            }
        }

        return res;
    }
}
