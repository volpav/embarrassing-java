package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.Reader;

import java.util.*;

public class TopKFrequentWords implements SolutionRunner {
    public void run(Scanner s) {
        String[] words = Reader.readStringArray(s);
        int k = s.nextInt();

        List<String> w = topKFrequent(words, k);

        for (String wo : w) {
            System.out.print(wo + ", ");
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> occur = new HashMap<>();

        for (String w : words) {
            if (occur.containsKey(w)) {
                occur.put(w, occur.get(w) + 1);
            } else {
                occur.put(w, 1);
            }
        }

        Arrays.sort(words, (x, y) -> {
            int comp = occur.get(y) - occur.get(x);

            if (comp == 0) {
                comp = x.compareTo(y);
            }

            return comp;
        });

        List<String> ret = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String w = words[i];

            ret.add(w);

            if (ret.size() == k) {
                break;
            }

            while (words[i].equals(w) && i < words.length) {
                i++;
            }

            i--;
        }

        return ret;
    }
}
