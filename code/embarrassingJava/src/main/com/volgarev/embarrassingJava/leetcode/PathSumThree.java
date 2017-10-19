package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.Reader;
import com.volgarev.embarrassingJava.util.TreeNode;

import java.util.*;

public class PathSumThree implements SolutionRunner {
    public void run(Scanner s) {
        TreeNode root = Reader.readBinaryTree(s);
        int sum = s.nextInt();

        System.out.println(pathSum(root, sum));
    }

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        return pathSumFrom(root, sum) +
                pathSum(root.left, sum) +
                pathSum(root.right, sum);
    }

    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }

        return (node.val == sum ? 1 : 0)+
                pathSumFrom(node.left, sum - node.val) +
                pathSumFrom(node.right, sum - node.val);
    }
}
