package com.hsl.algorithm.labuladong.dp;

import java.util.Arrays;

/**
 * 70. 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class ClimbStairs {


    public static void main(String[] args) {
        ClimbStairs main = new ClimbStairs();
        int res = main.climbStairs(3);
        System.out.println(res == 3);
    }

    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

//    int[] dp;

//    public int climbStairs(int n) {
//        dp = new int[n+1];
//        return recursion(n);
//    }

//    private int recursion(int n) {
//        if (n == 1) return 1;
//        if (n == 2) return 2;
//        if (dp[n] != 0) {
//            return dp[n];
//        }
//        dp[n] = recursion(n-1) + recursion(n-2);
//        return dp[n];
//    }
}
