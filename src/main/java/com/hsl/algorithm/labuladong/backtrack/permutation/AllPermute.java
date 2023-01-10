package com.hsl.algorithm.labuladong.backtrack.permutation;

import java.util.LinkedList;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 *
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 */
public class AllPermute {

    public static void main(String[] args) {
        AllPermute main = new AllPermute();
        List<List<Integer>> permute = main.permute(new int[]{1, 2, 3});
        System.out.println(permute);
    }


    List<List<Integer>> res = null;

    /**
     * 回溯算法框架示例
     * 1、路径：已经作出的选择
     * 2、选择列表：当前可以做到的选择
     * 3、结束条件：到达决策树底层，无法再做选择
     */
    public List<List<Integer>> permute(int[] nums) {
        res = new LinkedList<>();
        this.backtrack(nums, new boolean[nums.length], new LinkedList<>());
        return res;
    }

    private void backtrack(int[] nums, boolean[] used, LinkedList<Integer> cur) {
        // 结束条件
        if (cur.size() == nums.length) {
            res.add(new LinkedList<>(cur));
            return;
        }
        // 遍历选择列表
        for (int i = 0; i < nums.length; i++) {
            // 优化: 跳过已在路径中的选择
            if (used[i]) {
                continue;
            }
            // 做选择（路径）
            cur.offer(nums[i]);
            used[i] = true;
            // 进入下一层决策树
            this.backtrack(nums,used,cur);
            // 撤销选择
            cur.pollLast();
            used[i] = false;
        }
    }


//    List<List<Integer>> res = null;
//
//    public List<List<Integer>> permute(int[] nums) {
//        int len = 1;
//        for (int i = 2; i <= nums.length; i++) {
//            len = len * i;
//        }
//        res = new ArrayList<>(len);
//        this.recursion(nums, null);
//        return res;
//    }
//
//    private void recursion(int[] nums, List<Integer> seq) {
//        if (nums.length == 0) {
//            res.add(seq);
//            return;
//        }
//
//        for (int i = 0; i < nums.length; i++) {
//            if (seq == null) {
//                seq = new ArrayList<>(nums.length);
//            }
//            List<Integer> newSeq = new ArrayList<>(seq.size());
//            newSeq.addAll(seq);
//            newSeq.add(nums[i]);
//            int[] leftNums = new int[nums.length - 1];
//            for (int j = 0; j < nums.length; j++) {
//                if (j < i) {
//                    leftNums[j] = nums[j];
//                } else if (j > i){
//                    leftNums[j-1] = nums[j];
//                }
//            }
//            recursion(leftNums,newSeq);
//        }
//    }
}
