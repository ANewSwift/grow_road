package com.hsl.algorithm.labuladong.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
        int maxLen = main.lengthOfLongestSubstring2("abcabcbb");
        System.out.println(maxLen == 3);
//        int maxLen = main.lengthOfLongestSubstring("bbbbb");
//        System.out.println(maxLen == 1);
//        int maxLen = main.lengthOfLongestSubstring("pwwkew");
//        System.out.println(maxLen == 3);
//        int maxLen = main.lengthOfLongestSubstring(" ");
//        System.out.println(maxLen == 1);
    }

    public int lengthOfLongestSubstring2(String s) {
        int left = 0, right = 0;
        int maxLen = 0;
        Map<Character,Integer> windows = new HashMap<>();
        while (right < s.length()) {
            char rightC = s.charAt(right);
            right++;
            // 窗口内数据的一系列更新
            windows.put(rightC,windows.getOrDefault(rightC,0)+1);
            // 窗口内存在重复元素，则需收缩窗口
            while (windows.get(rightC) > 1) {
                char leftC = s.charAt(left);
                left++;
                // 窗口内数据的一系列更新
                windows.computeIfPresent(leftC, (k,v) -> v-1);
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        int maxLen = 0;
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            char rightC = s.charAt(right);
            right++;
            if (!set.contains(rightC)) {
                set.add(rightC);
            } else {
                // 收缩窗口
                while (set.contains(rightC)) {
                    char leftC = s.charAt(left);
                    set.remove(leftC);
                    left++;
                }
                set.add(rightC);
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }

    /**
     * 滑动窗口解法
     */
    public int lengthOfLongestSubstring_n(String s) {
        int maxLen = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            char rightC = s.charAt(right);
            right++;
            // 判断收缩窗口
            for (int i = left; i < right-1; i++) {
                if (rightC == s.charAt(i)) {
                    left = i+1;
                    break;
                }
            }
            // 更新结果
            maxLen = Math.max(maxLen,right-left);
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
