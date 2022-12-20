package com.hsl.algorithm.labuladong.linkedlist;

import com.hsl.algorithm.base.ListNode;

/**
 * leetcode 86.分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 */
public class Partition {
    public static void main(String[] args) {
        Partition main = new Partition();
//        int[] nums = {1,4,3,2,5,2};
        int[] nums = {2,1};
        ListNode partition = main.partition(ListNode.buildNode(nums), 2);
        System.out.println(partition.toString());
    }

    /**
     * 遍历链表，将小于x的节点移除原链表，并链接到小于x节点链表的尾部，这样根据x分隔为大小两个链表
     * 使原链表只包含大于等于x的节点，移除节点构成小于x的链表，最后将两个链表首位相接
     */
    ListNode partition(ListNode head, int x) {
        // 小于x的虚节点
        ListNode minDummy = new ListNode(-1);
        ListNode minList = minDummy;
        // 大于等于x的虚节点
        ListNode maxDummy = new ListNode(-1, head);
        ListNode p1 = maxDummy;
        ListNode p2 = head;
        while (p2 != null) {
            if (p2.val < x) {
                // 链接小于x的节点
                minList.next = p2;
                minList = minList.next;
                // 移除该节点
                p1.next = p2.next;
                p2 = p2.next;
            } else {
                p2 = p2.next;
                p1 = p1.next;
            }
        }
        // 小于x的尾节点，连接大于等于x的首节点
        minList.next = maxDummy.next;
        return minDummy.next;
    }
}
