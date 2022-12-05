package com.hsl.algorithm.labuladong.linkedlist;

import com.hsl.offer.base.ListNode;

/**
 * 剑指 Offer II 022. 链表中环的入口节点
 * 给定一个链表，返回链表开始入环的第一个节点。
 * 从链表的头节点开始沿着 next 指针进入环的第一个节点为环的入口节点。如果链表无环，则返回 null。
 */
public class DetectCycle {

    public static void main(String[] args) {
        DetectCycle main = new DetectCycle();
        int[] nums = {3,2,0,-4};
        ListNode head = ListNode.buildNode(nums);
        ListNode start = head.next;
        ListNode end = head;
        while (end.next != null) {
            end = end.next;
        }
        end.next = start;
        System.out.println(start == main.detectCycle(head));
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        // 找相遇点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        // 找起点
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
