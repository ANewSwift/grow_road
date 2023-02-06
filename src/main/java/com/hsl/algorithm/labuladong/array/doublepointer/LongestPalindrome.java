package com.hsl.algorithm.labuladong.array.doublepointer;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        LongestPalindrome main = new LongestPalindrome();
        String result = main.longestPalindrome("cbbd");
        System.out.println("bb".equals(result));
    }

    /**
     * 枚举以i/(i,i+1)为中心的所有回文串，从中找最大回文串
     */
    public String longestPalindrome(String s) {
        int left = 0, right = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int[] nums1 = this.expandCenter(s, i, i);
            int[] nums2 = this.expandCenter(s, i, i+1);
            int max = right - left;
            if (nums1[1] - nums1[0] > nums2[1] - nums2[0]) {
                if (nums1[1] - nums1[0] > max) {
                    left = nums1[0];
                    right = nums1[1];
                }
            } else {
                if (nums2[1] - nums2[0] > max) {
                    left = nums2[0];
                    right = nums2[1];
                }
            }
        }
        return s.substring(left, right+1);
    }

    public int[] expandCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return new int[]{left+1, right-1};
    }

    public String method2(String s) {
        String longestStr = "";
        for (int i = 0; i < s.length()-1; i++) {
            // 是否含有与i下标相等字符
            int idx = -1;
            for (int j = s.length()-1; j >i ; j--) {
                if (s.charAt(j) == s.charAt(i)) {
                    idx = j;
                }
                if (idx == -1) {
                    continue;
                }
                for (int a = i, b = idx; a < b; a++, b--) {
                    if (s.charAt(a) != s.charAt(b)) {
                        idx = -1;
                        break;
                    }
                }
                if (idx != -1) {
                    longestStr = (idx - i + 1) > longestStr.length() ? s.substring(i, idx+1) : longestStr;
                    break;
                }
            }
        }
        if (longestStr.length() == 0) {
            return s.substring(0,1);
        }
        return longestStr;
    }
}
