package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class TutorialIntro implements SolutionRunner {
    public void run(Scanner s) {
        int n = s.nextInt();
        int sz = s.nextInt();

        for (int i = 0; i < sz; i++) {
            int num = Integer.parseInt(s.next());

            if (num == n) {
                System.out.println(i);
                break;
            }
        }
    }
}
