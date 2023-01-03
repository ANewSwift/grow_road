package com.hsl.algorithm.labuladong.dp;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        LengthOfLIS main = new LengthOfLIS();
        int res = main.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
        System.out.println(res == 4);
    }

    /**
     * 动态规则 + 二分，时间复杂度O(NlogN)
     */
    public int lengthOfLIS(int[] nums) {
        // 定义以长度为下标的，递增序列末尾最小元素
        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];
        // 定义最长递增序列的长度
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > dp[len]) {
                dp[++len] = nums[i];
            } else {
                int left = 1;
                int right = len;
                while (left <= right) {
                    int mid = (left + right) >> 1;
                    if (nums[i] == dp[mid]) {
                        break;
                    } else if (nums[i] < dp[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                if (left>right) {
                    dp[left] = nums[i];
                }
            }
        }
        return len;
    }

//    /**
//     * 动态规划，时间复杂度O(N方)
//     */
//    public int lengthOfLIS(int[] nums) {
//        int max = 1;
//          定义以i小标结尾的递增子序列长度
//        int[] dp = new int[nums.length];
//        Arrays.fill(dp, 1);
//        for (int i = 1; i < nums.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if (nums[j] <= nums[i]) {
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//                }
//            }
//            max = Math.max(max, dp[i]);
//        }
//        return max;
//    }
}
