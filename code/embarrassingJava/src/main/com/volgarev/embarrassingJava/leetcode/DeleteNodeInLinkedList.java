package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.ListNode;
import com.volgarev.embarrassingJava.util.Reader;

import java.util.Scanner;

public class DeleteNodeInLinkedList implements SolutionRunner {
    public void run(Scanner s) {
        ListNode head = Reader.readLinkedList(s);
        int node = s.nextInt();

        ListNode cur = head;
        int i = 0;
        while (i++ < node) {
            cur = cur.next;
        }

        deleteNode(cur);

        cur = head;

        while (cur != null) {
            System.out.print(cur.val);

            if (cur.next != null) {
                System.out.print(", ");
            }

            cur = cur.next;
        }
    }

    public void deleteNode(ListNode node) {
        ListNode cur = node, next = node.next;

        while (next != null) {
            cur.val = next.val;

            if (next.next == null) {
                cur.next = null;
            }

            cur = next;
            next = next.next;
        }
    }
}
