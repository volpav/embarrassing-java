package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class FunnyString implements SolutionRunner {
    public void run(Scanner s) {
        int q = s.nextInt();

        for (int a0 = 0; a0 < q; a0++) {
            String str = s.next();
            boolean isFunny = isFunnyString(str);

            System.out.println(isFunny ? "Funny" : "Not Funny");
        }
    }

    private static boolean isFunnyString(String s) {
        String rev = new StringBuffer(s).reverse().toString();

        for (int i = 1; i < s.length(); i++) {
            if (Math.abs(((int)s.charAt(i)) - ((int)s.charAt(i - 1))) !=
                    Math.abs(((int)rev.charAt(i)) - ((int)rev.charAt(i - 1)))) {
                return false;
            }
        }

        return true;
    }
}
