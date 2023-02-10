package com.hsl.algorithm.labuladong.slidingwindow;

import java.util.*;

/**
 * 187. 重复的DNA序列
 *
 * DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。
 * 例如，"ACGAATTCCG" 是一个 DNA序列 。
 * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 *
 * 示例 2：
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 *
 * 提示：
 * 0 <= s.length <= 105
 * s[i]=='A'、'C'、'G' or 'T'
 */
public class FindRepeatedDnaSequences {

    public static void main(String[] args) {
        FindRepeatedDnaSequences main = new FindRepeatedDnaSequences();
        List<String> list = main.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        list.forEach(System.out::println);
    }

    public List<String> findRepeatedDnaSequences(String s) {
        // 数字位数
        int L = 10;
        // 进制
        int R = 4;
        // 存储 R^(L - 1) 的结果
        int RL = (int) Math.pow(R, L - 1);
        List<String> res = new LinkedList<>();
        Map<Integer,Integer> seen = new HashMap<>();
        int left = 0, right = 0;
        int windowHash = 0;
        while (right < s.length()) {
            // 低位增加一位
            windowHash = windowHash * R + this.hash(s.charAt(right));
            right++;
            if (right - left == L) {
                seen.put(windowHash, seen.getOrDefault(windowHash, 0) + 1);
                if (seen.get(windowHash) == 2) {
                    res.add(s.substring(left,right));
                }
                // 高位减少一位
                windowHash = windowHash - this.hash(s.charAt(left))*RL;
                left++;
            }
        }
        return res;
    }

    private int hash(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
            default:
                return 0;
        }
    }
}
