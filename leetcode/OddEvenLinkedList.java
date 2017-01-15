import java.util.Scanner;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class OddEvenLinkedList {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        ListNode head = new ListNode(1);
        ListNode cur = head;

        while ((n--) > 1) {
            cur.next = new ListNode(cur.val + 1);
            cur = cur.next;
        }

        printList(head);

        ListNode mod = new OddEvenLinkedList().oddEvenList(head);

        printList(mod);
    }

    private static void printList(ListNode head) {
        int i = 0;

        StringBuilder sb = new StringBuilder();

        if (head != null) {
            ListNode cur = head;

            while (cur != null) {
                sb.append(cur.val);
                sb.append("->");

                cur = cur.next;

                i++;

                if (i > 25) break;
            }
        }

        sb.append("NULL");

        System.out.println(sb.toString());
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        } 

        if (head.next == null) {
            return head;
        }

        ListNode ret = new ListNode(head.val);
        
        ret.next = new ListNode(head.next.val);

        ListNode lastOdd = ret;
        ListNode last = ret.next;
        ListNode cur = head.next.next;

        while (cur != null) {
            ListNode odd = cur;
            ListNode even = odd.next;
            ListNode tmp = lastOdd.next;

            if (even != null) {
                cur = even.next;

                last.next = even;
                last = last.next;
                last.next = null;

                lastOdd.next = odd;
                lastOdd.next.next = tmp; 

                lastOdd = lastOdd.next;
            } else {
                lastOdd.next = odd;
                lastOdd.next.next = tmp; 

                break;
            }
        }

        return ret;
    }
}