package com.hsl.algorithm.labuladong.linkedlist;

import com.hsl.offer.base.ListNode;

import javax.sound.midi.Soundbank;

/**
 * 876. 链表的中间结点
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 */
public class MiddleNode {

    public static void main(String[] args) {
        MiddleNode main = new MiddleNode();
        int[] nums = {1,2,3,4,5,6};
        ListNode middleNode = main.middleNode(ListNode.buildNode(nums));
        System.out.println(middleNode.toString());
    }

    public ListNode middleNode(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return p1;
    }
}
