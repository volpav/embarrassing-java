package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.*;

public class RansomNote implements SolutionRunner {
    public void run(Scanner s) {
        String ransomNote = s.next();
        String magazine = s.next();

        System.out.println(canConstruct(ransomNote, magazine));
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> available = new HashMap<>();
        Queue<Character> pending = new LinkedList<>();

        for (Character ch : ransomNote.toCharArray()) {
            pending.add(ch);
        }

        for (Character ch : magazine.toCharArray()) {
            available.put(ch, available.getOrDefault(ch, 0) + 1);
        }

        if (available.size() == 0 && pending.size() > 0) {
            return false;
        } else if (available.size() > 0 && pending.size() == 0) {
            return true;
        } else if (available.size() == 0 && pending.size() == 0) {
            return true;
        }

        while (pending.size() > 0) {
            Character next = pending.remove();

            Integer cnt = available.getOrDefault(next, 0);

            if (cnt == 0) {
                return false;
            }

            available.put(next, cnt - 1);
        }

        return true;
    }
}
