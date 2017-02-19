package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Permutations implements SolutionRunner {
    public void run(Scanner s) {
        char[] str = s.nextLine().toCharArray();
        int[] numbers = new int[str.length];

        for (int i = 0; i < str.length; i++) {
            numbers[i] = Character.getNumericValue(str[i]);
        }

        List<List<Integer>> ret = permute(numbers);

        for (List<Integer> p : ret) {
            System.out.print("[ ");
            
            int cnt = 0;

            for (Integer i : p) {
                System.out.print(i);

                cnt++;

                if (cnt < numbers.length) {
                    System.out.print(", ");
                }
            }
            
            System.out.println(" ]");
        }
    }

    public List<List<Integer>> permute(int[] numbers) {
        List<List<Integer>> ret = new ArrayList<>();

        if (numbers != null && numbers.length > 0) {
            List<Integer> single = new ArrayList<>();
            iterate(numbers, ret, single);
        }

        return ret;
    }

    private void iterate(int[] numbers, List<List<Integer>> all, List<Integer> single) {
        if (numbers.length == single.size()) {
            List<Integer> temp = new ArrayList<>(single);
            
            all.add(temp);
        }

        for (int num : numbers) {
            if (!single.contains(num)) {
                single.add(num);

                iterate(numbers, all, single);

                single.remove(single.size() - 1);
            }
        }
    }
}