package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.Interval;
import com.volgarev.embarrassingJava.util.Reader;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SingleNumberTwo implements SolutionRunner {
    public void run(Scanner s) {
        int[] nums = Reader.readArray(s);
        System.out.println(singleNumber(nums));
    }

    public int singleNumber(int[] nums) {
        Set<Integer> gen1 = new HashSet<>();
        Set<Integer> gen2 = new HashSet<>();

        for (int n : nums) {
            if (!gen1.contains(n)) {
                if (!gen2.contains(n)) {
                    gen1.add(n);
                }
            } else {
                gen1.remove(n);
                gen2.add(n);
            }
        }

        return (Integer)gen1.toArray()[0];
    }
}
