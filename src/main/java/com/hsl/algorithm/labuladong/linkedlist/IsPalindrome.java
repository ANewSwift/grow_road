package com.hsl.algorithm.labuladong.linkedlist;

import com.hsl.algorithm.base.ListNode;

/**
 * 234. 回文链表
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：head = [1,2,2,1]
 * 输出：true
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：false
 */
public class IsPalindrome {

    public static void main(String[] args) {
        IsPalindrome main = new IsPalindrome();
        boolean palindrome = main.isPalindrome(ListNode.buildNode(new int[]{1,2,2,1}));
        System.out.println(palindrome);
        boolean palindrome2 = main.isPalindrome(ListNode.buildNode(new int[]{1,2}));
        System.out.println(palindrome2);
    }

    /**
     * 反转链表对比后恢复，空间复杂度O1
     */
    public boolean isPalindrome(ListNode head) {
        // 1.找中点
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        // 2.反转中点后的链表
        ListNode reverseHead = this.reverse(slow);
        // 3.比较是否回文
        ListNode pr = reverseHead;
        ListNode pl = head;
        boolean res = true;
        while (pr != null) {
            if (pr.val != pl.val) {
                res = false;
            }
            pr = pr.next;
            pl = pl.next;
        }
        // 4.恢复原始链表结构
        this.reverse(reverseHead);
        return res;
    }

    private ListNode reverse(ListNode head) {
        ListNode p2 = null, p1 = head;
        while (p1 != null) {
            ListNode t = p1.next;
            p1.next = p2;
            p2 = p1;
            p1 = t;
        }
        return p2;
    }

    ListNode left;
    /**
     * 通过递归遍历，空间复杂度On
     */
    public boolean isPalindrome_recursion(ListNode head) {
        left = head;
        return this.traverse(head);
    }

    private boolean traverse(ListNode right) {
        if (right == null) {
            return true;
        }
        boolean res = this.traverse(right.next);
        res = res && (left.val == right.val);
        left = left.next;
        return res;
    }

    /**
     * 构建新的反转链表后对比，空间复杂度On
     */
    public boolean isPalindrome_newlist(ListNode head) {
        // 构建与head链表相反的新链表
        ListNode p = head;
        ListNode p1 = null;
        while (p != null) {
            p1 = new ListNode(p.val, p1);
            p = p.next;
        }
        // 复用p
        p = head;
        // 对比两个链表是否一致
        while (p != null) {
            if (p.val != p1.val) {
                return false;
            }
            p = p.next;
            p1 = p1.next;
        }
        return true;
    }
}
