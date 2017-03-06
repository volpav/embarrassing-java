package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.*;

public class FirstUniqueCharacterInAString implements SolutionRunner {
    public void run(Scanner s) {
        String str = s.next();
        System.out.println(firstUniqChar(str));
    }

    public int firstUniqChar(String s) {
        int ret = -1;

        if (s != null && s.length() > 0) {
            Set<Character> seen = new HashSet<Character>();
            Map<Character, Integer> firstIndex = new HashMap<Character, Integer>();

            char[] chars = s.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                Character ch = chars[i];

                if (!firstIndex.containsKey(ch)) {
                    if (!seen.contains(ch)) {
                        firstIndex.put(ch, i);
                        seen.add(ch);
                    }
                } else {
                    firstIndex.remove(ch);
                }
            }

            if (firstIndex.size() > 0) {
                int min = s.length();

                for (Map.Entry<Character, Integer> e : firstIndex.entrySet()) {
                    if (e.getValue() < min) {
                        min = e.getValue();
                    }
                }

                ret = min;
            }
        }

        return ret;
    }
}
