package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.Reader;
import com.volgarev.embarrassingJava.util.TreeNode;
import com.volgarev.embarrassingJava.util.Writer;

import java.util.Scanner;

public class DeleteNodeInBST implements SolutionRunner {
    public void run(Scanner s) {
        TreeNode root = Reader.readBinaryTree(s);
        int key = s.nextInt();

        TreeNode removed = deleteNode(root, key);

        Writer.writeBinaryTree(removed);
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, root.val);
        }

        return root;
    }

    private TreeNode findMin(TreeNode node){
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }
}
