package com.hsl.algorithm.labuladong.stocktrade;

import java.lang.management.ManagementFactory;

/**
 * 309. 最佳买卖股票时机含冷冻期
 *
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class MaxProfitFreeze {

    public static void main(String[] args) {
        MaxProfitFreeze main = new MaxProfitFreeze();
        int i = main.maxProfit_opt(new int[]{1,2,3,0,2});
        System.out.println(i == 3);
//        int i = main.maxProfit(new int[]{1});
//        System.out.println(i == 0);
//        int i = main.maxProfit_opt(new int[]{1,2,4});
//        System.out.println(i == 3);
    }

    public int maxProfit_opt(int[] prices) {
        int s1 = 0; // 今日未持有股票最大利润
        int s2 = Integer.MIN_VALUE; // 今日持有股票最大利润
        int s3 = 0; // 前天未持有股票的最大利润
        for (int i = 0; i < prices.length; i++) {
            int t = s1;
            s1 = Math.max(s1,s2 + prices[i]);
            s2 = Math.max(s2, s3 - prices[i]);
            s3 = t;
        }
        return s1;
    }

    /**
     * 动态规划
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        for (int i = 0; i < prices.length; i++) {
            if (i-1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            if (i-2 == -1) {
                dp[i][1] =  Math.max(dp[i-1][1], -prices[i]);
                continue;
            }
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);
        }
        return dp[prices.length-1][0];
    }
}
