package com.hsl.algorithm.labuladong.dp;

import java.util.Arrays;

/**
 * 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 */
public class CoinChange {

    public static void main(String[] args) {
        CoinChange main = new CoinChange();
        int res = main.coinChange(new int[]{1,2,5}, 11);
        System.out.println(res == 3);
//        int res = main.coinChange(new int[]{2}, 3);
//        System.out.println(res == -1);
    }

    public int coinChange(int[] coins, int amount) {
        int[] dps = new int[amount+1];
        Arrays.fill(dps, amount+1);
        dps[0] = 0;
        for (int i = 1; i < dps.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dps[i] = Math.min(dps[i], 1 + dps[i-coin]);
            }
        }
        return dps[amount] == amount + 1 ? -1 : dps[amount];
    }

    int[] dp;

    public int coinChangeRecursion(int[] coins, int amount) {
        dp = new int[amount + 1];
        Arrays.fill(dp, -2);
        return coinRecursion(coins, amount);
    }

    private int coinRecursion(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (dp[amount] != -2) {
            return dp[amount];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int preRes = coinRecursion(coins, amount - coins[i]);
            if (preRes == -1) {
                continue;
            }
            min = Math.min(min, 1 + preRes);
        }
        dp[amount] = (min == Integer.MAX_VALUE) ? -1 : min;
        return dp[amount];
    }
}
