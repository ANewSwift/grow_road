package com.hsl.algorithm.labuladong.slidingwindow;

import java.util.*;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 * 示例 2:
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 */
public class FindAnagrams {

    public static void main(String[] args) {
        FindAnagrams main = new FindAnagrams();
//        List<Integer> anagrams = main.findAnagrams("cbaebabacd", "abc");
//        System.out.println(anagrams);
        List<Integer> anagrams = main.findAnagrams("abab", "ab");
        System.out.println(anagrams);
    }

    Map<Character,Integer> needs = new HashMap<>();
    Map<Character,Integer> windows = new HashMap<>();
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int left = 0, right = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            needs.put(c,needs.getOrDefault(c,0)+1);
            windows.put(c,0);
        }
        while (right < s.length()) {
            char rightC = s.charAt(right);
            right++;
            // 更新窗口内容
            windows.computeIfPresent(rightC, (k,v) -> v+1);
            // 窗口长度等于目标串长度，则缩小窗口
            while (right - left == p.length()) {
                if (this.check()) {
                    res.add(left);
                }
                char leftC = s.charAt(left);
                // 窗口右移
                left++;
                // 更新窗口内容
                windows.computeIfPresent(leftC, (k,v) -> v-1);
            }
        }
        return res;
    }

    private boolean check() {
        Iterator<Map.Entry<Character, Integer>> iterator = needs.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            if (!windows.get(entry.getKey()).equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }
}
