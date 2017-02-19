package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class SingleNumber3 implements SolutionRunner {
    public void run(Scanner s) {
        int n = s.nextInt(), index = 0;
        int[] numbers = new int[n];

        while ((n--) > 0) {
            numbers[index++] = s.nextInt();
        }

        int[] ret = singleNumber(numbers);
        System.out.println(String.format("[ %d, %d ]", ret[0], ret[1]));
    }

    public int[] singleNumber(int[] numbers) {
        Set<Integer> cur = new HashSet<>();
        
        for (int num : numbers) {
            if (cur.contains(num)) {
                cur.remove(num);
            } else {
                cur.add(num);
            }
        }

        Integer[] temp = new Integer[2];
        cur.toArray(temp);
        
        return new int[] { temp[0], temp[1] };
    }
}