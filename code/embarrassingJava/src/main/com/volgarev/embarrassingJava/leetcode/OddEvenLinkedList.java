package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.ListNode;

import java.util.Scanner;

public class OddEvenLinkedList implements SolutionRunner {
    public void run(Scanner s) {
        int n = s.nextInt();

        ListNode head = new ListNode(1);
        ListNode cur = head;

        while ((n--) > 1) {
            cur.next = new ListNode(cur.val + 1);
            cur = cur.next;
        }

        printList(head);

        ListNode mod = oddEvenList(head);

        printList(mod);
    }

    public static void printList(ListNode head) {
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

    private ListNode oddEvenList(ListNode head) {
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