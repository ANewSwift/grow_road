package com.hsl.algorithm.labuladong.array.twod;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 151. 反转字符串中的单词
 *
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 * 示例 1：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 *
 * 示例 2：
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：反转后的字符串中不能存在前导空格和尾随空格。
 *
 * 示例 3：
 * 输入：s = "a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
 *
 * 提示：
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 * 进阶：如果字符串在你使用的编程语言中是一种可变数据类型，请尝试使用 O(1) 额外空间复杂度的 原地 解法。
 */
public class ReverseWords {
    public static void main(String[] args) {
        ReverseWords main = new ReverseWords();
//        System.out.println(main.reverseWords("  hello    world   ").equals("hello world"));
//        System.out.println(main.reverseWords("  hello    world   ").equals("dlrow olleh"));
        System.out.println(main.reverseWords("  hello    world   ").equals("world hello"));

    }

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int end = s.length() - 1;
        while (end >= 0) {
            if (s.charAt(end) == ' ') {
                end--;
                continue;
            }
            int start = end - 1;
            while (start >= 0 && s.charAt(start) != ' ') {
                start--;
            }
            for (int i = start+1; i <= end; i++) {
                sb.append(s.charAt(i));
            }
            end = start;
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public String reverseWords1(String s) {
        // 去除空格
        StringBuilder sb = this.trimSpaces(s);
        // 反转整个字符串
        this.reverseStr(sb,0,sb.length()-1);
        // 反转每个单词
        this.reverseEachWord(sb);
        return sb.toString();
    }

    /**
     * 去重字符串多余空格
     * @param s
     * @return
     */
    private StringBuilder trimSpaces(String s) {
        int left = 0,right = s.length() - 1;
        while (left<=right && s.charAt(left) == ' ') {
            left++;
        }
        while (left<=right && s.charAt(right) == ' ') {
            right--;
        }
        StringBuilder sb = new StringBuilder();
        for (; left <= right; left++) {
            char c = s.charAt(left);
            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length()-1) != ' ') {
                sb.append(c);
            }
        }
        return sb;
    }

    /**
     * 反转指定区间字符
     * @param sb
     * @param start
     * @param end
     */
    private void reverseStr(StringBuilder sb, int start, int end) {
        while (start < end) {
            char c = sb.charAt(start);
            sb.setCharAt(start++, sb.charAt(end));
            sb.setCharAt(end--, c);
        }
    }


    /**
     * 反转每个单词
     * @param sb
     */
    private void reverseEachWord(StringBuilder sb) {
        int start = 0, end = 0;
        while (start < sb.length()) {
            while (end < sb.length() && sb.charAt(end) != ' ') {
                end++;
            }
            this.reverseStr(sb, start,end-1);
            end++;
            start = end;
        }
    }

    public String reverseWords2(String s) {
        String trim = s.trim();
        List<String> strings = Arrays.asList(trim.split("\\s+"));
        Collections.reverse(strings);
        return String.join(" ", strings);
    }
}
