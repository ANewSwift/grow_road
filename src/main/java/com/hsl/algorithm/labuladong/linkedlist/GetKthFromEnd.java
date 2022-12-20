package com.hsl.algorithm.labuladong.linkedlist;

import com.hsl.algorithm.base.ListNode;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 */
public class GetKthFromEnd {

    public static void main(String[] args) {
        GetKthFromEnd main = new GetKthFromEnd();
        int[] nodes = {1,2,3,4,5};
        ListNode kthFromEnd = main.getKthFromEnd(ListNode.buildNode(nodes), 2);
        System.out.println(kthFromEnd.toString());
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k < 1) {
            return null;
        }
        ListNode p1 = head;
        // 先走k-1步
        for (int i = 0; i < k-1 && p1 != null; i++) {
            p1 = p1.next;
        }
        // 如果k大于链表长度，则返回null
        if (p1 == null) {
            return null;
        }
        ListNode p2 = head;
        while (p1.next != null) {
            p1 = p1.next;
            p2= p2.next;
        }
        return p2;
    }
}
