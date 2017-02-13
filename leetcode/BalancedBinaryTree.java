class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
 
      TreeNode(int x) { val = x; }
 }
   
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (root.left == null && root.right == null) {
            return true;
        }

        return Math.abs(getDepth(root.left) - getDepth(root.right)) <= 1 &&
            isBalanced(root.left) && isBalanced(root.right);
    }

    private int getDepth(TreeNode node) {
        if (node == null) {
            return 1;
        }

        return Math.max(getDepth(node.left) + 1, getDepth(node.right) + 1);
    }
}