package com.hsl.algorithm.labuladong.stocktrade;

/**
 * 714. 买卖股票的最佳时机含手续费
 *
 * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 示例 1：
 * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
 *
 * 示例 2：
 * 输入：prices = [1,3,7,5,10,3], fee = 3
 * 输出：6
 */
public class MaxProfitFee {

    public static void main(String[] args) {
        MaxProfitFee main = new MaxProfitFee();
        int i = main.maxProfit_dp_opt(new int[]{1, 3, 2, 8, 4, 9}, 2);
        System.out.println(i == 8);
//        int i = main.maxProfit_dp_opt(new int[]{1,3,7,5,10,3}, 3);
//        System.out.println(i == 6);
    }

    public int maxProfit_dp_opt(int[] prices, int fee) {
        int s1 = 0; // 今日未持有股票的最大利润
        int s2 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            s1 = Math.max(s1, s2 + prices[i]);
            s2 = Math.max(s2, s1 - prices[i] - fee);
        }
        return s1;
    }

    /**
     * 动态规划
     */
    public int maxProfit_dp(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];
        for (int i = 0; i < prices.length; i++) {
            if (i-1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i]-fee;
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i] - fee);
        }
        return dp[prices.length-1][0];
    }
}
