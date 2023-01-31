package com.hsl.algorithm.labuladong.stocktrade;

/**
 * 121. 买卖股票的最佳时机
 *
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 示例 1：
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * 示例 2：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class MaxProfit {

    public static void main(String[] args) {
        MaxProfit main = new MaxProfit();
        int profit = main.maxProfit_dp_opt(new int[]{7, 1, 5, 3, 6, 4});
        System.out.println(profit == 5);
//        int profit = main.maxProfit(new int[]{7,6,4,3,1});
//        System.out.println(profit == 0);
//        int profit = main.maxProfit_dp(new int[]{1,2});
//        System.out.println(profit == 1);
    }

    /**
     * 动态规划，空间优化版
     */
    public int maxProfit_dp_opt(int[] prices) {
        int s1 = 0; // 昨天未持有股票
        int s2 = Integer.MIN_VALUE; // 昨天持有股票
        for (int i = 0; i < prices.length; i++) {
            s1 = Math.max(s1, s2 + prices[i]);
            s2 = Math.max(s2, -prices[i]);
        }
        return s1;
    }

    /**
     * 动态规划
     */
    public int maxProfit_dp(int[] prices) {
        int[][] dp = new int[prices.length][2];
        for (int i = 0; i < prices.length; i++) {
            if (i-1 == -1) {
                // base case
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }
        return dp[prices.length-1][0];
    }

    /**
     * 暴力优化版
     */
    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i]>=minPrice) {
                continue;
            }
            minPrice = prices[i];
            for (int j = i+1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit,profit);
            }
        }
        return maxProfit;
    }
}
