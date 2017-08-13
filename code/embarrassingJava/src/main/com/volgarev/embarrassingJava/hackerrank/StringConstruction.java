package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.*;

public class StringConstruction implements SolutionRunner {
    public void run(Scanner s) {
        int q = s.nextInt();

        for (int a0 = 0; a0 < q; a0++){
            String str = s.next();
            int result = stringConstruction(str);

            System.out.println(result);
        }
    }

    static int stringConstruction(String s) {
        int ret = 0;
        boolean[] exists = new boolean[256];

        for (Character ch : s.toCharArray()) {
            int index = ch - 'a';

            if (!exists[index]) {
                ret++;
                exists[index] = true;
            }
        }

        return ret;
    }
}
