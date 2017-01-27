import java.util.Scanner;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        ListNode head = null, cur = null;
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        while ((n--) > 0) {
            int v = s.nextInt();

            if (head == null) {
                head = new ListNode(v);
                cur = head;
            } else {
                cur.next = new ListNode(v);
                cur = cur.next;
            }
        }

        cur = head;

        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }

        System.out.println("");

        ListNode mod = new RemoveDuplicatesFromSortedList().deleteDuplicates(head);

        cur = mod;

        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;

        while (cur != null) {
            if (cur.next != null) {
                if (cur.next.val == cur.val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return head;
    }
}