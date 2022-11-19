package com.hsl.algorithm.labuladong.linkedlist;

import com.hsl.offer.base.ListNode;

import java.util.List;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class MergeTwoList {

    public static void main(String[] args) {
        MergeTwoList main = new MergeTwoList();
        int[] list1 = {1,2,4};
        int[] list2 = {1,3,4};
        ListNode head = main.mergeTwoLists(ListNode.buildNode(list1), ListNode.buildNode(list2));
        System.out.println(head.toString());
    }

    /**
     * 遍历两个链表，用一指针p，链接两节点中较小者
     * 指针p类似缝衣线，将两个链表，按大小顺序缝在一起，形成一条链表
     */
    ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0,null);
        ListNode p = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }
        p.next = list1 == null ? list2 : list1;
        return dummy.next;
    }
}
