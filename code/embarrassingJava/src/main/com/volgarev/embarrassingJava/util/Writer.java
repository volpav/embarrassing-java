package com.volgarev.embarrassingJava.util;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Writer {
    public static void writeIntArray(int[] items) {
        writeIntArray(items, items.length);
    }

    public static void writeIntArray(int[] items, int size) {
        Integer[] copied = new Integer[size];

        for (int i = 0; i < size; i++) {
            copied[i] = items[i];
        }

        writeArray(copied);
    }

    public static <T> void writeArray(T[] items) {
        writeArray(items, items.length);
    }

    public static <T> void writeArray(T[] items, int size) {
        System.out.print("[ ");

        for (int i = 0; i < size; i++) {
            System.out.print(items[i]);

            if (i < (size - 1)) {
                System.out.print(",");
            }

            System.out.print(" ");
        }

        System.out.println("]");
    }

    public static void writeBinaryTree(TreeNode root) {
        List<String> values = new ArrayList<>();
        Queue<TreeNode> nodes = new LinkedList<>();

        nodes.add(root);

        while (nodes.size() > 0) {
            TreeNode n = nodes.remove();

            if (n != null) {
                values.add(Integer.toString(n.val));

                nodes.add(n.left);
                nodes.add(n.right);
            } else {
                values.add("null");
            }
        }

        int leafNulls = 0;

        for (int i = values.size() - 1; i >= 0; i--) {
            if (values.get(i).equals("null")) {
                leafNulls++;
            } else {
                break;
            }
        }

        int finalSize = values.size() - leafNulls;
        String[] items = new String[finalSize];

        for (int i = 0; i < finalSize; i++) {
            items[i] = values.get(i);
        }

        writeArray(items);
    }
}
