package com.hsl.algorithm.labuladong.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 51. N 皇后
 */
public class NQueens {

    public static void main(String[] args) {
        NQueens main = new NQueens();
        List<List<String>> lists = main.solveNQueens(4);
        System.out.println(lists);
    }

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }
        this.backtrack(board, 0);
        return res;
    }

    public void backtrack(char[][] board, int row) {
        if (row == board.length) {
            res.add(Arrays.stream(board).map(String::valueOf).collect(Collectors.toList()));
            return;
        }
        for (int j = 0; j < board[row].length; j++) {
            // 检查
            if (!this.isValidate(board, row, j)) {
                continue;
            }
            // 做选择
            board[row][j] = 'Q';
            // 进入下一层决策树
            this.backtrack(board, row+1);
            // 撤销选择
            board[row][j] = '.';
        }
    }

    private boolean isValidate(char[][] board, int x, int y) {
        // 设置同行 或 同列 或 斜对角
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int offsetY = i - x;
                // 同行 或 同列 或 斜对角 有皇后
                if (board[i][j] == 'Q' &&
                    ((i == x || j == y) || (j == (y + offsetY) || j == (y - offsetY)))) {
                        return false;
                }
            }
        }
        return true;
    }

}
