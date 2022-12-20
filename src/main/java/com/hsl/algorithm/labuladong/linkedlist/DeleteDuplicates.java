package com.hsl.algorithm.labuladong.linkedlist;

import com.hsl.algorithm.base.ListNode;

/**
 * 83. 删除排序链表中的重复元素
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 */
public class DeleteDuplicates {

    public static void main(String[] args) {
        DeleteDuplicates main = new DeleteDuplicates();
        int[] nums = {1,1,2,3,3};
        ListNode listNode = main.deleteDuplicates(ListNode.buildNode(nums));
        System.out.println(listNode.toString());
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p = head;
        while (p.next != null) {
            if (p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }
}
