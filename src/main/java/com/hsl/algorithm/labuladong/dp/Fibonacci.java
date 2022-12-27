package com.hsl.algorithm.labuladong.dp;

public class Fibonacci {

    public static void main(String[] args) {
        Fibonacci main = new Fibonacci();
        System.out.println(main.fib(6) == 8);
    }

    /**
     * 动态规划,优化内存版
     * 自底向上
     */
    public int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int v1 = 1;
        int v2 = 1;
        int v3 = v1 + v2;
        for (int i = 4; i <= n; i++) {
            v3 = v1 + v2;
            v2 = v3;
            v1 = v2;
        }
        return v3;
    }

    /**
     * 动态规划
     * 自底向上
     */
    public int fib_db(int n) {
        int[] arr = new int[n+1];
        arr[1] = 1;
        arr[2] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[n];
    }

    /**
     * 递归,带存储版
     * 自顶向下
     */
    public int fib_recursion_storage(int n) {
        int[] arr = new int[n+1];
        arr[1] = 1;
        arr[2] = 1;
        return traverse_recursion(n,arr);
    }

    private int traverse_recursion(int n, int[] arr) {
        if (arr[n] != 0) {
            return arr[n];
        }
        arr[n] = traverse_recursion(n-1,arr) + traverse_recursion(n-2, arr);
        return arr[n];
    }


    /**
     * 递归求解
     * 自顶向下
     */
    public int fib_recursion(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib_recursion(n - 1) + fib_recursion(n - 2);
    }
}
