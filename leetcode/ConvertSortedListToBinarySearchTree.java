import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) { val = x; }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) { val = x; }
}

public class ConvertSortedListToBinarySearchTree {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        ListNode head = new ListNode(-1),
            cur = head;

        while ((n--) > 0) {
            cur.next = new ListNode(s.nextInt());
            cur = cur.next;
        }

        head = head.next;

        TreeNode tree = new ConvertSortedListToBinarySearchTree().sortedListToBST(head);

        return;
    }

    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> items = linkedListToList(head);
        
        if (items.size() == 0) {
            return null;
        }

        return toNode(items, 0, items.size() - 1);
    }

    private TreeNode toNode(List<Integer> items, int left, int right) {
        if (right - left > 0) {
            int half = left + ((right - left) / 2);

            TreeNode n = new TreeNode(items.get(half));

            n.left = toNode(items, left, half - 1);
            n.right = toNode(items, half + 1, right);

            return n;
        } else {
            if (left == right) {
                return new TreeNode(items.get(left));
            } else {
                return null;
            }
            
        }
    }

    private List<Integer> linkedListToList(ListNode head) {
        List<Integer> ret = new ArrayList<Integer>();

        ListNode n = head;

        while (n != null) {
            ret.add(n.val);
            n = n.next;
        }

        return ret;
    }
}