import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) { val = x; }
}

public class FindLeftMostElement {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(14);
        root.left.left = new TreeNode(1);

        System.out.println(new FindLeftMostElement().findLeftMostNode(root));
    }

    public int findLeftMostNode(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();

        List<List<Integer>> levelValues = new ArrayList<List<Integer>>();

        q.add(root);

        int level = 0;

        while (q.size() > 0) {
            int nodesOnLevel = ((int)Math.pow(2, level));

            levelValues.add(new ArrayList<Integer>());

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