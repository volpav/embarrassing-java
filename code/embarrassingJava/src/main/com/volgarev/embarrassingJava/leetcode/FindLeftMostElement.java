package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.TreeNode;

import java.util.*;

public class FindLeftMostElement implements SolutionRunner {
    public void run(Scanner s) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(14);
        root.left.left = new TreeNode(1);

        System.out.println(findLeftMostNode(root));
    }

    public int findLeftMostNode(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();

        List<List<Integer>> levelValues = new ArrayList<>();

        q.add(root);

        int level = 0;

        while (q.size() > 0) {
            int nodesOnLevel = ((int)Math.pow(2, level));

            levelValues.add(new ArrayList<>());

            while ((nodesOnLevel--) > 0) {
                TreeNode cur = q.remove();

                levelValues.get(level).add(cur == null ? Integer.MIN_VALUE : cur.val);

                if (cur != null) {
                    q.add(cur.left != null ? cur.left : null);
                    q.add(cur.right != null ? cur.right : null);
                } else {
                    q.add(null);
                    q.add(null);
                }
            }

            boolean hasMoreNodes = false;
            List<Integer> lastLevel = levelValues.get(levelValues.size() - 1);
            
            for (Integer i : lastLevel) {
                if (i > Integer.MIN_VALUE) {
                    hasMoreNodes = true;
                    break;
                }
            }
            
            if (!hasMoreNodes) {
                levelValues.remove(levelValues.size() - 1);
                break;
            }
            

            level++;
        }

        List<Integer> lastLevel = levelValues.get(levelValues.size() - 1);
        int ret = -1;

        for (Integer i : lastLevel) {
            if (i > Integer.MIN_VALUE) {
                ret = i;
                break;
            }
        }

        return ret;
    }
}