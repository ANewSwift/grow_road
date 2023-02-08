package com.hsl.algorithm.labuladong.array.twod;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 59. 螺旋矩阵 II
 *
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 *
 * 提示：
 * 1 <= n <= 20
 */
public class GenerateMatrix {

    public static void main(String[] args) {
        GenerateMatrix main = new GenerateMatrix();
        int[][] ints = main.generateMatrix(4,6);
        for (int[] v : ints) {
            String str = Arrays.stream(v).mapToObj(String::valueOf).collect(Collectors.joining(","));
            System.out.println(str);
        }
    }

    public int[][] generateMatrix(int n) {
        return this.generateMatrix(n,n);
    }

    public int[][] generateMatrix(int m, int n) {
        int[][] matrix = new int[m][n];
        int upperBound = 0, lowerBound = m - 1;
        int leftBound = 0, rightBound = n - 1;
        int val = 0;
        while (val < m * n) {
            // 上边界，从左到右
            if (upperBound <= lowerBound) {
                for (int i = leftBound; i <= rightBound; i++) {
                    matrix[upperBound][i] = ++val;
                }
                upperBound++;
            }
            // 右边界，从上到下
            if (leftBound <= rightBound) {
                for (int i = upperBound; i <= lowerBound; i++) {
                    matrix[i][rightBound] = ++val;
                }
                rightBound--;
            }
            // 下边界，从右到左
            if (upperBound <= lowerBound) {
                for (int i = rightBound; i >= leftBound ; i--) {
                    matrix[lowerBound][i] = ++val;
                }
                lowerBound--;
            }
            // 左边界，从下到上
            if (leftBound <= rightBound) {
                for (int i = lowerBound; i >= upperBound ; i--) {
                    matrix[i][leftBound] = ++val;
                }
                leftBound++;
            }
        }
        return matrix;
    }
}
