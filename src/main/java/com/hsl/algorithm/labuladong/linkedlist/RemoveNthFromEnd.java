package com.hsl.algorithm.labuladong.linkedlist;

import com.hsl.algorithm.base.ListNode;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class RemoveNthFromEnd {

    public static void main(String[] args) {
        RemoveNthFromEnd main = new RemoveNthFromEnd();
        int[] nums = {1,2,3,4,5};
        ListNode listNode = main.removeNthFromEnd2(ListNode.buildNode(nums), 2);
        System.out.println(listNode.toString());
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null || n < 1) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        GetKthFromEnd getKthFromEnd = new GetKthFromEnd();
        ListNode kthFromEnd = getKthFromEnd.getKthFromEnd(dummy, n + 1);
        if (kthFromEnd == null) {
            return null;
        }
        kthFromEnd.next = kthFromEnd.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null || n < 1) {
            return null;
        }
        ListNode p1 = head;
        for (int i = 0; i < n-1 && p1 != null; i++) {
            p1 = p1.next;
        }
        if (p1 == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p2 = dummy;
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p2.next = p2.next.next;
        return dummy.next;
    }
}
