package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Arrays;
import java.util.Scanner;

public class BigSorting implements SolutionRunner {
    public void run(Scanner s) {
        int n = s.nextInt();
        String[] unsorted = new String[n];

        for (int unsorted_i = 0; unsorted_i < n; unsorted_i++) {
            unsorted[unsorted_i] = s.next();
        }

        sort(unsorted);

        for (int sorted_i = 0; sorted_i < n; sorted_i++) {
            System.out.println(unsorted[sorted_i]);
        }
    }

    private static String[] sort(String[] input) {
        Arrays.sort(input, (x, y) -> {
           int ret = x.length() - y.length();

           if (ret == 0) {
               for (int i = 0; i < x.length(); i++) {
                   int ch1 = (int)x.charAt(i);
                   int ch2 = (int)y.charAt(i);

                   ret = ch1 - ch2;

                   if (ret != 0) {
                       break;
                   }
               }
           }

           return ret;
        });

        return input;
    }
}
