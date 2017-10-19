package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.Reader;
import com.volgarev.embarrassingJava.util.TreeNode;

import java.util.*;

public class FindModeInBinarySearchTree implements SolutionRunner {
    public void run(Scanner s) {
        TreeNode root = Reader.readBinaryTree(s);

        int[] result = findMode(root);

        for (Integer i : result) {
            System.out.print(i + " ");
        }
    }

    public int[] findMode(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();

        int max = 0;
        Map<Integer, Integer> occur = new HashMap<>();

        if (root != null) {
            q.add(root);
        }

        while (!q.isEmpty()) {
            TreeNode n = q.remove();

            int key = n.val;
            int newOccur = occur.getOrDefault(key, 0) + 1;

            occur.put(key, newOccur);
            if (newOccur > max) {
                max = newOccur;
            }

            if (n.left != null) {
                q.add(n.left);
            }

            if (n.right != null) {
                q.add(n.right);
            }
        }

        List<Integer> ret = new ArrayList<>();

        for (Integer key : occur.keySet()) {
            if (occur.get(key) == max) {
                ret.add(key);
            }
        }

        int[] arr = new int[ret.size()];

        for (int i = 0; i < ret.size(); i++) {
            arr[i] = ret.get(i);
        }

        return arr;
    }
}
