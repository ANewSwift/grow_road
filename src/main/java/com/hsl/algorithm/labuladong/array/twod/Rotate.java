package com.hsl.algorithm.labuladong.array.twod;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 48. 旋转图像
 *
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 *
 * 示例 2：
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 * 提示：
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
public class Rotate {

    public static void main(String[] args) {
        Rotate main = new Rotate();
//        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        main.rotate(matrix);
        for (int[] m : matrix) {
            String str = Arrays.stream(m).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(","));
            System.out.println(str);
        }
    }

    /**
     * 顺时针90度，主对角线对折+垂直翻转
     */
    public void rotate(int[][] matrix) {
        // 对折
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i+1; j < matrix[i].length; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
        // 每行反转
        for (int[] m : matrix) {
            for (int left = 0, right = m.length-1; left < right; left++,right--) {
                int t = m[left];
                m[left] = m[right];
                m[right] = t;
            }
        }
    }

    /**
     * 顺时针90度，主对角线对折+水平翻转
     */
    public void rotate_reverse_90(int[][] matrix) {
        // 对折
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i+1; j < matrix[i].length; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
        // 上下翻转
        for (int i = 0; i < matrix.length / 2; i++) {
            int[]  t = matrix[i];
            matrix[i] = matrix[matrix.length - 1 - i];
            matrix[matrix.length - 1 - i] = t;
        }
    }
}
