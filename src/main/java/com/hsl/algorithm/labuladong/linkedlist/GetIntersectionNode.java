package com.hsl.algorithm.labuladong.linkedlist;

import com.hsl.algorithm.base.ListNode;

/**
 * 160. 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回 null 。
 */
public class GetIntersectionNode {

    public static void main(String[] args) {
        int[] nums1 = {4,1};
        int[] nums2 = {5,6,1};
        int[] same = {8,4,5};
        ListNode sameNode = ListNode.buildNode(same);
        ListNode headA = ListNode.buildNode(nums1);
        ListNode headB = ListNode.buildNode(nums2);
        ListNode p1 = headA, p2 = headB;
        while (p1.next != null) {
            p1 = p1.next;
        }
        p1.next = sameNode;
        while (p2.next != null) {
            p2 = p2.next;
        }
        p2.next = sameNode;
        GetIntersectionNode main = new GetIntersectionNode();
        ListNode intersectionNode = main.getIntersectionNode(headA, headB);
        System.out.println(intersectionNode == sameNode);
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            if (p1 == null) p1 = headB;
            else p1 = p1.next;
            if (p2 == null) p2 = headA;
            else p2 = p2.next;
        }
        return p1;
    }

    /**
     * 通过预先计算长度，然后对齐求解
     */
    public ListNode methodLength(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0;
        for (ListNode p1 = headA; p1 != null; p1 = p1.next) {
            lenA++;
        }
        for (ListNode p2 = headB; p2 != null; p2 = p2.next) {
            lenB++;
        }
        ListNode p1 = headA,p2 = headB;
        if (lenA > lenB) {
            int subtract = lenA - lenB;
            for (int i = 0; i < subtract; i++) {
                p1 = p1.next;
            }
        } else {
            int subtract = lenB - lenA;
            for (int i = 0; i < subtract; i++) {
                p2 = p2.next;
            }
        }
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
