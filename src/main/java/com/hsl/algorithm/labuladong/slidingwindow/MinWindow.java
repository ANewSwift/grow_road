package com.hsl.algorithm.labuladong.slidingwindow;

import java.util.*;

/**
 * 76. 最小覆盖子串
 *
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 *
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 *
 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 */
public class MinWindow {

    public static void main(String[] args) {
        MinWindow main = new MinWindow();
        String s = main.minWindow("ADOBECODEBANC", "ABC");
        System.out.println("BANC".equals(s));
//        String s = main.minWindow("a", "a");
//        System.out.println("a".equals(s));
//        String s = main.minWindow("aa", "aa");
//        System.out.println("aa".equals(s));
//        String s = main.minWindow("a", "aa");
//        System.out.println("".equals(s));
//        String s = main.minWindow("bba", "ab");
//        System.out.println("ba".equals(s));
    }

    Map<Character,Integer> needs = new HashMap<>();
    Map<Character,Integer> windows = new HashMap<>();

    // 示例： s = "ADOBECODEBANC", t = "ABC"
    public String minWindow(String s, String t) {
        needs = new HashMap<>();
        windows = new HashMap<>();
        // 左右边界
        int left = 0;
        int right = 0;
        // 最小串的起始索引，及最小长度
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            needs.put(c, needs.getOrDefault(c, 0) + 1);
            windows.put(c, 0);
        }
        while (right < s.length()) {
            char rightC = s.charAt(right);
            right++;
            if (windows.containsKey(rightC)) {
                windows.put(rightC, windows.get(rightC) + 1);
            }
            // 滑动窗口包含所有目标串字符
            while (this.containsAll()) {
                // 更新结果
                int len = right - left;
                if (len < minLen) {
                    minLen = len;
                    start = left;
                }
                // 更新windows
                char leftC = s.charAt(left);
                if (windows.containsKey(leftC)) {
                    windows.put(leftC, windows.get(leftC) - 1);
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start+minLen);
    }

    private boolean containsAll() {
        Iterator<Map.Entry<Character, Integer>> iterator = needs.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            if (windows.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

//    /**
//     * 废弃（写的烂）
//     */
//    public String minWindow2(String s, String t) {
//        if (s == null || s.length() == 0) {
//            return "";
//        }
//        int minLen = Integer.MAX_VALUE;
//        String minStr = "";
//        int left,right,len;
//        // flag数组中true元素，为滑动窗口中是否包含t中字符
//        boolean[] flag = new boolean[t.length()];
//        Arrays.fill(flag, false);
//        // 寻找s中包含t中字符的第一个值
//        int firstIdx = this.getFirstIdx(s, t, 0);
//        if (firstIdx == -1) {
//            return "";
//        } else {
//            left = firstIdx;
//            right = firstIdx;
//            this.addFlag(t,flag,s.charAt(firstIdx));
//            len = 1;
//            if (t.length() == 1) {
//                return t;
//            }
//        }
//        // 滑动窗口，右移右边界
//        while (++right < s.length()) {
//            len++;
//            char rightC = s.charAt(right);
//            // 不需要左移左边界
//            // 1.右边界元素不是目标串中的字符，则继续循环右移右边界
//            int rightInTIdx = t.indexOf(rightC);
//            if (rightInTIdx == -1) {
//                continue;
//            }
//            // 2.滑动窗口内的字符，没有完全包含目标串中的字符
//            if (!allTrue(flag)) {
//                this.addFlag(t,flag,rightC);
//            }
//            else {
//                // 需要左移左边界
//                // 滑动窗口内元素已全部包含目标串中的字符 且 right元素在滑动窗口存在
//                int rightInWindowLastIdx = -1;
//                for (int i = left; i < right; i++) {
//                    if (rightC == s.charAt(i)) {
//                        rightInWindowLastIdx = i;
//                    }
//                }
//                if (rightInWindowLastIdx != -1) {
//                    // 移除
//                    for (int i = left; i < rightInWindowLastIdx; i++) {
//                        this.removeFlag(t,flag,s.charAt(i));
//                    }
//                    int firstIdx2 = this.getFirstIdx(s, t, rightInWindowLastIdx+1);;
//                    left = firstIdx2;
//                    len = right - left + 1;
//                }
//            }
//            if (allTrue(flag)) {
//                if (len < minLen) {
//                    minLen = len;
//                    minStr = s.substring(left,right+1);
//                }
//            }
//        }
//        return minStr;
//    }

//    private void addFlag(String t, boolean[] flag, char c) {
//        for (int i = 0; i < t.length(); i++) {
//            if (c == t.charAt(i) && !flag[i]) {
//                flag[i] = true;
//                break;
//            }
//        }
//    }
//
//    private void removeFlag(String t, boolean[] flag, char c) {
//        for (int i = 0; i < t.length(); i++) {
//            if (c == t.charAt(i) && flag[i]) {
//                flag[i] = false;
//                break;
//            }
//        }
//    }

//    private boolean allTrue(boolean[] flag) {
//        for (int i = 0; i < flag.length; i++) {
//            if (!flag[i]) {
//                return false;
//            }
//        }
//        return true;
//    }

//    private int getFirstIdx(String s, String t, int fromSIndex) {
//        for (int i = fromSIndex; i < s.length(); i++) {
//            int index = t.indexOf(s.charAt(i));
//            if (index != -1) {
//                return i;
//            }
//        }
//        return -1;
//    }
}
