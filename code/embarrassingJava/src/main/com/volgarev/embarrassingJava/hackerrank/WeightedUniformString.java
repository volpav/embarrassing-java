package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WeightedUniformString implements SolutionRunner {
    public void run(Scanner s) {
        Set<Integer> weights = buildUniformWeights(s.next());

        int q = s.nextInt();

        while(q-- > 0) {
            System.out.println(weights.contains(s.nextInt()) ? "Yes" : "No");
        }
    }

    private Set<Integer> buildUniformWeights(String s) {
        Set<Integer> ret = new HashSet<>();

        if (s.length() > 0) {
            int curChar = 0,
                curWeight = getWeight(s.charAt(curChar));

            ret.add(curWeight);

            for (int i = 1; i < s.length(); i++) {
                char ch = s.charAt(i);

                if (ch == s.charAt(curChar)) {
                    curWeight += getWeight(ch);
                } else {
                    curChar = i;
                    curWeight = getWeight(ch);
                }

                ret.add(curWeight);
            }
        }

        return ret;
    }

    private int getWeight(char ch) {
        return ((int)ch) - ((int)'a') + 1;
    }
}
