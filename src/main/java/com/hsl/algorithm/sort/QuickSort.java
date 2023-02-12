package com.hsl.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 挑选最后一个标志，小的放左边，大的放右边
 * 分别对左右两部分，做递归
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {3,2,5,7,1,4};
        Arrays.stream(arr).forEach(System.out::print);
        QuickSort ins = new QuickSort();
        ins.quickSort(arr,0,arr.length-1);
        System.out.println("");
        Arrays.stream(arr).forEach(System.out::println);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = this.partition(arr, left, right);
        this.quickSort(arr, left, pivot - 1);
        this.quickSort(arr, pivot + 1, right);
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = right;
        int needChange = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < arr[pivot]) {
                this.swap(arr, i, needChange);
                needChange++;
            }
        }
        this.swap(arr, needChange, pivot);
        return needChange;
    }


    private void quickSort2(int[] arr, int start, int end) {
        // 给定范围只要一项，不需排序
        if (start == end) {
            return;
        }
        int pivot = partition2(arr, start, end);
        if (pivot > start) {
            quickSort2(arr, start, pivot-1);
        }
        if (pivot < end) {
            quickSort2(arr, pivot+1, end);
        }
    }


    /**
     * 针对给定数组分左右两部分，pivot=end，左边小于pivot，右边大于pivot
     * 实现：小于arr[end]，放在左边，由small下标控制，small为已遍历小于arr[end]的下一个下标
     * @param arr 目标数组
     * @param start 开始下标
     * @param end 结束下标
     * @return pivot
     */
    private int partition2(int[] arr, int start, int end) {
        // small 为所有小于pivot的下一个
        int small = start;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {
                if (small != i) {
                    swap(arr, i, small);
                }
                small++;
            }
        }
        // 把pivot放到正确位置
//        small++;
        swap(arr, end, small);
        return small;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
