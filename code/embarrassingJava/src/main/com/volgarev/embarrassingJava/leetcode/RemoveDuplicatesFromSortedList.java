package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.ListNode;

import java.util.Scanner;

public class RemoveDuplicatesFromSortedList implements SolutionRunner {
    public void run(Scanner s) {
        ListNode head = null, cur = null;

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

        ListNode mod = deleteDuplicates(head);

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
                cur = null;
            }
        }

        return head;
    }
}