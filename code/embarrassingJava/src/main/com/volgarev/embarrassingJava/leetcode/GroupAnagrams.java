package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class GroupAnagrams implements SolutionRunner {
    public void run(Scanner s) {
        int n = s.nextInt(), idx = 0;
        String[] inputArr = new String[n];
        
        while ((n--) > 0) {
            inputArr[idx++] = s.next();
        }

        List<List<String>> grouped = groupAnagrams(inputArr);

        System.out.println("[");
        for (List<String> group : grouped) {
            System.out.print("  [ ");
            for (int i = 0; i < group.size(); i++) {
                System.out.print(String.format("\"%s\"", group.get(i)));
                if (i < group.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(" ]");
        }
        System.out.println("]");
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<>();
        Map<String, Integer> index = new HashMap<>();

        for (String str : strs) {
            String canonical = getCanonical(str);

            if (!index.containsKey(canonical)) {
                int idx = ret.size();
                ret.add(new ArrayList<>());
                index.put(canonical, idx);
            } 

            ret.get(index.get(canonical)).add(str);
        }

        return ret;        
    }

    private String getCanonical(String str) {
        char[] chars = str.toCharArray();

        Arrays.sort(chars);

        return String.valueOf(chars);
    }
}