package com.volgarev.embarrassingJava.misc;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FirstNonRecurringCharacter implements SolutionRunner {
    public void run(Scanner s) {
        System.out.println(firstNonRecurringChar(s.next()));
    }

    private char firstNonRecurringChar(String s) {
        PriorityQueue<Integer> nonRecur = new PriorityQueue<>();
        Map<Character, Integer> nonRecurPos = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (!nonRecurPos.containsKey(ch)) {
                nonRecur.add(i);
                nonRecurPos.put(ch, i);
            } else {
                nonRecur.remove(nonRecurPos.get(ch));
            }
        }

        return !nonRecur.isEmpty() ? s.charAt(nonRecur.remove()) : '\0';
    }
}
