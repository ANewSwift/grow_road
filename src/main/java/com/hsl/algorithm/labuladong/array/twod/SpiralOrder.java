package com.hsl.algorithm.labuladong.array.twod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 54. 螺旋矩阵
 *
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class SpiralOrder {

    public static void main(String[] args) {
        SpiralOrder main = new SpiralOrder();
//        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        List<Integer> integers = main.spiralOrder(matrix);
        String str = integers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("->"));
        System.out.println(str);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        List<Integer> res = new ArrayList<>(m*n);
        // 定义上下左右边界
        int upperBound = 0, lowerBound = m - 1;
        int leftBound = 0, rightBound = n - 1;
        while (res.size() < m * n) {
            // 上边界，从左到右遍历
            if (upperBound <= lowerBound) {
                for (int i = leftBound; i <= rightBound; i++) {
                    res.add(matrix[upperBound][i]);
                }
                upperBound++;
            }
            // 右边界，从上到下遍历
            if (leftBound <= rightBound) {
                for (int i = upperBound; i <= lowerBound; i++) {
                    res.add(matrix[i][rightBound]);
                }
                rightBound--;
            }
            // 下边界，从右到左遍历
            if (upperBound <= lowerBound) {
                for (int i = rightBound; i >= leftBound ; i--) {
                    res.add(matrix[lowerBound][i]);
                }
                lowerBound--;
            }
            // 左边界，从下到上遍历
            if (leftBound <= rightBound) {
                for (int i = lowerBound; i >= upperBound; i--) {
                    res.add(matrix[i][leftBound]);
                }
                leftBound++;
            }
        }
        return res;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        // 代表螺旋一圈
        for (int c = 0; c < (matrix.length + 1) / 2; c++) {
            // 进行螺旋遍历
            // 从左到右
            if (c > n-c-1) {
                break;
            }
            for (int i = c; i < n - c; i++) {
                res.add(matrix[c][i]);
            }
            // 从右到下
            if (c+1 > m-c-1) {
                break;
            }
            for (int i = c+1; i < m - c; i++) {
                res.add(matrix[i][n - c - 1]);
            }
            // 从右到左边
            if (n - c - 2 < c) {
                break;
            }
            for (int i = n-c-2; i >= c ; i--) {
                res.add(matrix[m - c - 1][i]);
            }
            // 从下到上
            for (int i = m - c - 2; i > c; i--) {
                res.add(matrix[i][c]);
            }
        }
        return res;
    }
}
