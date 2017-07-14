package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class BirthdayCakeCandles implements SolutionRunner {
    public void run(Scanner s) {
        int n = s.nextInt();
        int[] ar = new int[n];
        for(int ar_i = 0; ar_i < n; ar_i++){
            ar[ar_i] = s.nextInt();
        }
        int result = birthdayCakeCandles(n, ar);
        System.out.println(result);
    }

    int birthdayCakeCandles(int n, int[] ar) {
        int max = 0, cnt = 0;

        if (ar.length > 0) {
            max = ar[0];
            cnt = 1;

            for (int i = 1; i < ar.length; i++) {
                if (ar[i] > max) {
                    max = ar[i];
                    cnt = 1;
                } else if (ar[i] == max) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
