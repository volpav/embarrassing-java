package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Arrays;
import java.util.Scanner;

public class MiniMaxSum implements SolutionRunner {
    public void run(Scanner s) {
        int[] arr = new int[5];

        for (int arr_i=0; arr_i < 5; arr_i++) {
            arr[arr_i] = s.nextInt();
        }

        Arrays.sort(arr);

        long min = 0, max = 0;

        for (int i = 0; i < 4; i++) {
            min += arr[i];
            max += arr[arr.length - i - 1];
        }

        System.out.println(String.format("%d %d", min, max));
    }
}
