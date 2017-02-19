package com.volgarev.embarrassingJava.misc;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;
import java.util.*;

public class Anagram implements SolutionRunner {
    public void run(Scanner s) {
        char[] str1 = s.next().toLowerCase().toCharArray(),
            str2 = s.next().toLowerCase().toCharArray();

        System.out.println("Map: " + (IsAnagramMap(str1, str2) ? "It is an anagram!" : "Not an anagram..."));
        System.out.println("Sort: " + (IsAnagramSort(str1, str2) ? "It is an anagram!" : "Not an anagram..."));
    }

    /**
     * Checks whether the two strings (represented as arrays of characters) are anagrams.
     * This method uses character occurrences for comparison. Running time is O(n), space complexity is O(n).
     * 
     * @param str1 First string.
     * @param str2 Second string.
     * @return Value indicating whether input strings are anagrams.
     */
    private static boolean IsAnagramMap(char[] str1, char[] str2) {
        if (str1.length != str2.length) {
            return false;
        }

        Map<Character, Integer> occur1 = new HashMap<Character, Integer>();
        Map<Character, Integer> occur2 = new HashMap<Character, Integer>();

        for (int i = 0; i < str1.length; i++) {
            char ch1 = str1[i];
            char ch2 = str2[i];

            occur1.put(ch1, occur1.getOrDefault(ch1, 0) + 1);
            occur2.put(ch2, occur2.getOrDefault(ch2, 0) + 1);
        }

        if (occur1.size() != occur2.size()) {
            return false;
        }

        for (Character key : occur1.keySet()) {
            if (!occur2.containsKey(key) || occur2.get(key) != occur1.get(key)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks whether the two strings (represented as arrays of characters) are anagrams.
     * This method sorts the arrays and checks for equality. Running time is O(n + n * log(n)).
     * 
     * @param str1 First string.
     * @param str2 Second string.
     * @return Value indicating whether input strings are anagrams.
     */
    private static boolean IsAnagramSort(char[] str1, char[] str2) {
        if (str1.length != str2.length) {
            return false;
        }

        Arrays.sort(str1);
        Arrays.sort(str2);

        for (int i = 0; i < str1.length; i++) {
            if (str1[i] != str2[i]) {
                return false;
            }
        }

        return true;
    }
}