package com.hsl.algorithm.labuladong.array.prefixsum;

/**
 * 304. 二维区域和检索 - 矩阵不可变
 *
 * 给定一个二维矩阵 matrix，以下类型的多个请求：
 * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2) 。
 * 实现 NumMatrix 类：
 * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2) 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和 。
 *
 * 示例 1：
 * 输入:
 * ["NumMatrix","sumRegion","sumRegion","sumRegion"]
 * [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
 * 输出:
 * [null, 8, 11, 12]
 *
 * 解释:
 * NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
 * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
 * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
 */
public class NumMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        NumMatrix main1 = new NumMatrix(matrix);
        System.out.println(main1.sumRegion(2,1,4,3) == 8);
        System.out.println(main1.sumRegion(1,1,2,2) == 11);
        System.out.println(main1.sumRegion(1,2,2,4) == 12);
    }

    int[][] preMatrix;

    public NumMatrix(int[][] matrix) {
        preMatrix = new int[matrix.length+1][matrix[0].length+1];
        for (int i = 1; i < preMatrix.length; i++) {
            for (int j = 1; j < preMatrix[0].length; j++) {
                preMatrix[i][j] = matrix[i-1][j-1] + preMatrix[i-1][j] + preMatrix[i][j-1] - preMatrix[i-1][j-1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        row1++;
        col1++;
        row2++;
        col2++;
        return preMatrix[row2][col2] - preMatrix[row1-1][col2] - preMatrix[row2][col1-1] + preMatrix[row1-1][col1-1];
    }
}
