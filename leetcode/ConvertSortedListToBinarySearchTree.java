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
    private int[] items;

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
        linkedListToList(head);
        
        if (items.length == 0) {
            return null;
        }
        
        return toNode(0, items.length - 1);
    }

    private TreeNode toNode(int left, int right) {
        if (right - left > 0) {
            int half = left + ((right - left) / 2);

            TreeNode n = new TreeNode(items[half]);

            n.left = toNode(left, half - 1);
            n.right = toNode(half + 1, right);

            return n;
        } else {
            if (left == right) {
                return new TreeNode(items[left]);
            } else {
                return null;
            }
        }
    }

    private void linkedListToList(ListNode head) {
        int count = 0, i = 0;

        ListNode n = head;

        while (n != null) {
            count++;
            n = n.next;
        }

        items = new int[count];

        n = head;

        while (n != null) {
            items[i++] = n.val;
            n = n.next;
        }
    }
}