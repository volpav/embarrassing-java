package com.volgarev.embarrassingJava.util;

import java.util.Scanner;

public class Reader {
    public static Interval[] readIntervals(Scanner s) {
        int n = s.nextInt();

        int index = 0;
        Interval[] ret = new Interval[n];

        while((n--) > 0) {
            int start = s.nextInt(),
                end = s.nextInt();

            ret[index++] = new Interval(start, end);
        }

        return ret;
    }

    public static int[] readArray(Scanner s) {
        return readArray(s, s.nextInt());
    }

    public static int[] readArray(Scanner s, int n) {
        int index = 0;
        int[] ret = new int[n];

        while ((n--) > 0) {
            ret[index++] = s.nextInt();
        }

        return ret;
    }

    public static String[] readStringArray(Scanner s) { return readStringArray(s, s.nextInt()); }

    public static String[] readStringArray(Scanner s, int n) {
        int index = 0;
        String[] ret = new String[n];

        while ((n--) > 0) {
            ret[index++] = s.next();
        }

        return ret;
    }

    public static TreeNode readBinaryTree(Scanner s) {
        int n = s.nextInt();

        if (n <= 0) {
            return null;
        }

        int index = 0;
        String[] values = new String[n];
        TreeNode[] nodes = new TreeNode[n];

        while ((n--) > 0) {
            values[index++] = s.next();
        }

        for (int i = 0; i < values.length; i++) {
            TreeNode node = nodes[i];

            if (node == null) {
                node = createNode(values[i]);
            }

            if (node != null) {
                int left = 2 * i + 1, right = 2 * i + 2;

                if (left < values.length) {
                    node.left = nodes[left] = createNode(values[left]);
                }

                if (right < values.length) {
                    node.right = nodes[right] = createNode(values[right]);
                }

                nodes[i] = node;
            }
        }

        return nodes[0];
    }

    private static TreeNode createNode(String input) {
        if (!input.equals("null")) {
            int val = Integer.parseInt(input);
            return new TreeNode(val);
        } else {
            return null;
        }
    }
}
