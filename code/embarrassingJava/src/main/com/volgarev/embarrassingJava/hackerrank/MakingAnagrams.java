package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class MakingAnagrams implements SolutionRunner {
    public void run(Scanner s) {
        String s1 = s.next();
        String s2 = s.next();

        int result = makingAnagrams(s1, s2);

        System.out.println(result);
    }

    static int makingAnagrams(String s1, String s2){
        int offset = 'a';
        int[] counts = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            counts[s1.charAt(i) - offset]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            counts[s2.charAt(i) - offset]--;
        }

        int notEnoughCharacters = 0,
                extraCharacters = 0;

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] < 0) {
                extraCharacters += Math.abs(counts[i]);
            } else if (counts[i] > 0) {
                notEnoughCharacters += counts[i];
            }
        }

        int min = Math.min(notEnoughCharacters, extraCharacters);
        int rem = Math.abs(notEnoughCharacters - extraCharacters);

        return (min * 2) + rem;
    }
}
