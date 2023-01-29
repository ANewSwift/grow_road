package com.hsl.algorithm.labuladong.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        LengthOfLongestSubstring main = new LengthOfLongestSubstring();
//        int maxLen = main.lengthOfLongestSubstring_n("abcabcbb");
//        System.out.println(maxLen == 3);
        int maxLen = main.lengthOfLongestSubstring_n("bbbbb");
        System.out.println(maxLen == 1);
//        int maxLen = main.lengthOfLongestSubstring_n("pwwkew");
//        System.out.println(maxLen == 3);
    }

    /**
     * 滑动窗口解法
     */
    public int lengthOfLongestSubstring_n(String s) {
        int maxLen = 1;
        int len = 1;
        int left = 0;
        int right = 0;
        while (++right < s.length()) {
            len++;
            for (int i = left; i < right; i++) {
                if (s.charAt(right) == s.charAt(i)) {
                    len = len - (i - left) - 1;
                    left = i+1;
                    break;
                }
            }
            if (len > maxLen) {
                maxLen = len;
            }
        }
        return maxLen;
    }

    /**
     * 暴力破解，空间复杂度优化（时间复杂度会降低）
     */
    public int lengthOfLongestSubstring_n2_opt(String s) {
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int len = 1;
            for (int j = i+1; j < s.length(); j++) {
                boolean contains = false;
                for (int k = i; k < j; k++) {
                    if (s.charAt(j) == s.charAt(k)) {
                        contains = true;
                    }
                }
                if (contains) {
                    break;
                } else {
                    len++;
                }
            }
            if (len > maxLen) {
                maxLen = len;
            }
        }
        return maxLen;
    }

    /**
     * 暴力破解
     */
    public int lengthOfLongestSubstring_n2(String s) {
        int maxLen = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.clear();
            set.add(s.charAt(i));
            int len = 1;
            for (int j = i+1; j < s.length(); j++) {
                if (set.contains(s.charAt(j))) {
                    break;
                } else {
                    set.add(s.charAt(j));
                    len++;
                }
            }
            if (len > maxLen) {
                maxLen = len;
            }
        }
        return maxLen;
    }
}
