package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.Reader;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Triangle implements SolutionRunner {
    public void run(Scanner s) {
        int h = s.nextInt();

        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        int cur = 1;

        while (h-- > 0) {
            int[] row = Reader.readArray(s, cur++);
            triangle.add(Arrays.stream(row).boxed().collect(Collectors.toList()));
        }

        System.out.println(minimumTotal(triangle));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size() + 1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                A[j] = Math.min(A[j], A[j + 1]) + triangle.get(i).get(j);
            }
        }

        return A[0];
    }

    private int minimumTotal(List<List<Integer>> triangle, int level, int levelPosition, int sum) {
        if (level >= triangle.size() - 1) {
            return sum;
        }

        int value = triangle.get(level).get(levelPosition);

        int bottomLeftSibling = levelPosition;
        int bottomRightSibling = levelPosition + 1;

        int bottomLeftValue = triangle.get(level + 1).get(bottomLeftSibling);
        int bottomRightValue = triangle.get(level + 1).get(bottomRightSibling);

        if (bottomLeftValue != bottomRightValue) {
            int lesserCost = Math.min(bottomLeftValue, bottomRightValue);

            sum = minimumTotal(
                    triangle,
                    level + 1,
                    lesserCost == bottomLeftValue ?
                            bottomLeftSibling :
                            bottomRightSibling,
                    sum + lesserCost
            );
        } else {
            sum = Math.min(
                minimumTotal(
                    triangle,
                    level + 1,
                    bottomLeftSibling,
                    sum + bottomLeftValue
                ),
                minimumTotal(
                    triangle,
                    level + 1,
                    bottomRightSibling,
                    sum + bottomRightValue
                )
            );
        }

        return sum;
    }
}
