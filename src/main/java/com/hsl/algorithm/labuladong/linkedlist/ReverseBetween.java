package com.hsl.algorithm.labuladong.linkedlist;

import com.hsl.algorithm.base.ListNode;

/**
 * 92. 反转链表 II
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 */
public class ReverseBetween {
    public static void main(String[] args) {
        ReverseBetween main = new ReverseBetween();
        ListNode listNode1 = main.reverseBetween(ListNode.buildNode(new int[]{1,2,3,4,5}), 2, 4);
        System.out.println(listNode1);
        ListNode listNode2 = main.reverseBetween(ListNode.buildNode(new int[]{5}), 1, 1);
        System.out.println(listNode2);
        ListNode listNode3 = main.reverseBetween(ListNode.buildNode(new int[]{3,5}), 1, 2);
        System.out.println(listNode3);
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 反转后的链表头不固定，使用虚拟节点避免复杂讨论
        ListNode dummyNode = new ListNode(Integer.MIN_VALUE);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        // 1.找到left的前一个节点
        for (int i = 0; i < left-1; i++) {
            pre = pre.next;
        }
        // 2.找到right节点
        ListNode rightNode = pre;
        for (int i = left-1; i < right; i++) {
            rightNode = rightNode.next;
        }
        // 3.切出待反转的链表
        ListNode leftNode = pre.next;
        ListNode post = rightNode.next;

        // 4.切断链接
        pre.next = null;
        rightNode.next = null;

        // 5.反转链表子区间
        this.reverseListNode(leftNode);

        // 6.链接链表
        pre.next = rightNode;
        leftNode.next = post;
        return dummyNode.next;
    }

    private ListNode reverseListNode(ListNode head) {
        ListNode p1 = null, p2 = head;
        while (p2 != null) {
            ListNode t = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = t;
        }
        return p1;
    }

    /**
     * 逻辑不清晰，容易出错
     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        if (head == null || left > right) {
            return null;
        }
        if (left == right) {
            return head;
        }
        int idx = 1;
        // 找left前一节点
        ListNode p1 = head;
        while (p1 != null && idx < left - 1) {
            p1 = p1.next;
            idx++;
        }
        ListNode pre = left == 1 ? null : p1;

        ListNode tail;
        if(left == 1) {
            tail = head;
        } else {
            tail = p1.next;
            idx++;
        }

        ListNode p2 = tail;
        p1 = null;
        while (p2 != null && idx <= right) {
            ListNode t = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = t;
            idx++;
        }

        if (pre != null) {
            pre.next = p1;
        }
        if (tail != null) {
            tail.next = p2;
        }
        return pre == null ? p1 : head;
    }
}
