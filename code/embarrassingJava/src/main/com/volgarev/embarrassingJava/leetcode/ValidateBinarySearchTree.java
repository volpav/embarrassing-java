package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.TreeNode;

import java.util.Scanner;

public class ValidateBinarySearchTree implements SolutionRunner {
    public void run(Scanner s) {

    }

    public boolean isValidBST(TreeNode root) {
        return isValidNode(root);
    }

    private boolean isValidNode(TreeNode node) {
        boolean isValid = node == null || (
            (node.left == null || (node.left.val < node.val && isSmaller(node.left, node.val))) &&
            (node.right == null || (node.right.val > node.val && isGreater(node.right, node.val)))
        );

        if (!isValid) {
            return false;
        }

        if (node != null) {
            return isValidNode(node.left) && isValidNode(node.right);
        } else return true;
    }

    private boolean isSmaller(TreeNode node, int v) {
        return node == null || (node.val < v && isSmaller(node.left, v) && isSmaller(node.right, v));
    }

    private boolean isGreater(TreeNode node, int v) {
        return node == null || (node.val > v && isGreater(node.left, v) && isGreater(node.right, v));
    }
}