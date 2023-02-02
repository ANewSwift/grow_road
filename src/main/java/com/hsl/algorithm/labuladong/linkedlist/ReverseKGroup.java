package com.hsl.algorithm.labuladong.linkedlist;

import com.hsl.algorithm.base.ListNode;

/**
 * 25. K 个一组翻转链表
 *
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 */
public class ReverseKGroup {
    public static void main(String[] args) {
        ReverseKGroup main = new ReverseKGroup();
//        ListNode listNode = main.reverseKGroup(ListNode.buildNode(new int[]{1, 2, 3, 4, 5}), 2);
//        System.out.println(listNode);
        ListNode listNode = main.reverseKGroup(ListNode.buildNode(new int[]{1, 2, 3, 4, 5}), 3);
        System.out.println(listNode);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode newHead = head;
        int idx1 = 1;
        int idx2 = 1;
        ListNode p1 = head;
        ListNode p2 = head;
        while (p1 != null) {
            p1 = p1.next;
            idx1++;
            if (idx1 % k == 0 && p1 != null) {
                newHead = this.reverseBetween(newHead, idx2, idx1);
                p1 = p2;
                p2 = p2.next;
                idx2 = idx1+1;
            }
        }
        return newHead;
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
}
