package com.hsl.algorithm.labuladong.linkedlist;

import com.hsl.algorithm.base.ListNode;

import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class MergeKLists {

    public static void main(String[] args) {
        MergeKLists main = new MergeKLists();
        int[][] nums = {{1,4,5},{1,3,4},{2,6}};
        ListNode[] lists = {ListNode.buildNode(nums[0]),ListNode.buildNode(nums[1]),ListNode.buildNode(nums[2])};
        ListNode head = main.mergeKLists(lists);
        System.out.println(head.toString());
    }

    /**
     * 优先级队列解法
     */
    ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        // 虚拟头结点
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        // 优先级队列，最小堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                lists.length, (a, b)->(a.val - b.val));
        // 将 k 个链表的头结点加入最小堆
        for (ListNode head : lists) {
            if (head != null)
                pq.add(head);
        }

        while (!pq.isEmpty()) {
            // 获取最小节点，接到结果链表中
            ListNode node = pq.poll();
            p.next = node;
            if (node.next != null) {
                pq.add(node.next);
            }
            // p 指针不断前进
            p = p.next;
        }
        return dummy.next;
    }


//    public ListNode mergeKLists(ListNode[] lists) {
//        if (lists == null || lists.length == 0) {
//            return null;
//        }
//        ListNode dummy = new ListNode(-1);
//        ListNode p = dummy;
//        while (p != null) {
//            p.next = findMinListNode(lists);
//            p = p.next;
//        }
//        return dummy.next;
//    }
//    ListNode findMinListNode(ListNode[] lists) {
//        int minIndex = -1;
//        for (int i = 0; i < lists.length; i++) {
//            if (lists[i] == null) {
//                continue;
//            }
//            if (minIndex == -1) {
//                minIndex = i;
//                continue;
//            }
//            if (lists[minIndex].val > lists[i].val) {
//                minIndex = i;
//            }
//        }
//        if (minIndex == -1) {
//            return null;
//        } else {
//            ListNode t = lists[minIndex];
//            lists[minIndex] = lists[minIndex].next;
//            return t;
//        }
//    }
}
