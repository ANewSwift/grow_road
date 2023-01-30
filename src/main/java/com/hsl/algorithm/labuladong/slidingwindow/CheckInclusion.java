package com.hsl.algorithm.labuladong.slidingwindow;

import java.util.*;

/**
 * 567. 字符串的排列
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。
 * 如果是，返回 true ；否则，返回 false 。
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 * 示例 1：
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 *
 * 示例 2：
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 */
public class CheckInclusion {

    public static void main(String[] args) {
        CheckInclusion main = new CheckInclusion();
        boolean b = main.checkInclusion("ab", "eidbaooo");
        System.out.println(b);

//        boolean b = main.checkInclusion("ab", "eidboaoo");
//        System.out.println(b);

//        boolean b = main.checkInclusion("hello", "ooolleoooleh");
//        System.out.println(b);

//        boolean b = main.checkInclusion("adc", "dcda");
//        System.out.println(b);
    }
    Map<Character,Integer> needs = new HashMap<>();
    Map<Character,Integer> windows = new HashMap<>();
    // 示例：s1 = "hello" s2 = "ooolleoooleh"
    // 示例：s1= "ab" s2 = "eidboaoo"
    public boolean checkInclusion(String s1, String s2) {
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            needs.put(c, needs.getOrDefault(c,0)+1);
            windows.put(c,0);
        }
        int left = 0;
        int right = 0;
        while (right < s2.length()) {
            char rightC = s2.charAt(right);
            right++;
            if (windows.containsKey(rightC)) {
                windows.put(rightC, windows.get(rightC)+1);
            }
            while (right - left == s1.length()) {
                // 如果窗口内包含s1全排列，立即返回结果
                if (this.check()) {
                    return true;
                }
                char leftC = s2.charAt(left);
                // 窗口右移
                left++;
                // 更新窗口内容
                if (windows.containsKey(leftC)) {
                    windows.put(leftC, windows.get(leftC)-1);
                }
            }
        }
        return false;
    }

    private boolean check() {
        Iterator<Map.Entry<Character, Integer>> iterator = needs.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            if (!Objects.equals(windows.get(entry.getKey()), entry.getValue())) {
                return false;
            }
        }
        return true;
    }
}
