package com.volgarev.embarrassingJava.ctci;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.*;

public class Scratchpad implements SolutionRunner {
    public void run(Scanner s) {
        ArraysAndStrings_1_2(s);
    }

    private void ArraysAndStrings_1_1(Scanner s) {
        String str = s.next();

        Set<Character> chars = new HashSet<>();

        for (char ch : str.toCharArray()) {
            if (chars.contains(ch)) {
                System.out.println("No...");
                return;
            } else {
                chars.add(ch);
            }
        }

        System.out.println("Yes!");
    }

    private void ArraysAndStrings_1_2(Scanner s) {
        String str1 = s.next(),
            str2 = s.next();

        Map<Character, Integer> occur = new HashMap<>();

        for (char ch : str1.toCharArray()) {
            if (occur.containsKey(ch)) {
                occur.put(ch, occur.get(ch) + 1);
            } else {
                occur.put(ch, 1);
            }
        }

        for (char ch : str2.toCharArray()) {
            if (occur.containsKey(ch)) {
                Integer cnt = occur.get(ch);

                cnt--;

                if (cnt == 0) {
                    occur.remove(ch);
                } else {
                    occur.put(ch, cnt);
                }
            } else {
                System.out.println("No...");
                return;
            }
        }

        System.out.println(occur.isEmpty() ? "Yes!" : "No...");
    }
}
