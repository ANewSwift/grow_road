package com.hsl.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        HeapSort main = new HeapSort();
//        int[] arr = new int[]{3, 5, 4, 1, 2, 6};
//        main.maxHeapSort(arr);
//        Arrays.stream(arr).forEach(System.out::println);
        int[] arr = new int[]{4,1,3,2,16,9,10,14,8,7};
        main.maxHeapSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    public void maxHeapSort(int[] arr) {
        // 建大顶堆
        this.buildMaxHeap(arr);
        // 堆排序
        // 从最后一个元素开始，与第一个元素交换；堆大小减一，并维护最大堆特性
        int heapSize = arr.length;
        for (int i = arr.length - 1; i > 0; i--) {
            int t = arr[i];
            arr[i] = arr[0];
            arr[0] = t;
            this.adjMaxHeap(arr, --heapSize, 0);
        }
    }

    /**
     * 构建大顶堆
     */
    private void buildMaxHeap(int[] arr) {
        // 从最后一个非叶节点开始，依次调整使每个parent节点都满足最大堆特性
        for (int i = (arr.length -2) / 2; i >= 0; i--) {
            this.adjMaxHeap(arr, arr.length, i);
        }
    }

    /**
     * 调整以idx为堆顶的子堆，使其满足大顶堆特性
     */
    private void adjMaxHeap(int[] arr, int heapSize, int idx) {
        if (idx >= heapSize) {
            return;
        }
        // 找三个节点中最大的
        int left = (idx << 1) + 1;
        int right = left + 1;
        int max = idx;
        if (left < heapSize && arr[left] > arr[idx]) {
            max = left;
        }
        if (right < heapSize && arr[right] > arr[max]) {
            max = right;
        }
        // 最大的不为parent，则交换parent与max，且递归max，使其满足最大堆特性
        if (max != idx) {
            int t = arr[idx];
            arr[idx] = arr[max];
            arr[max] = t;
            this.adjMaxHeap(arr, heapSize, max);
        }
    }

    /**
     * 最小堆排序
     */
    private void minHeapSort(int[] arr) {
        // 构建最小堆
        this.buildMinHeap(arr);
        // 从最后一个元素开始，到第二个元素为止，每个元素与第一个元素交换；堆大小减一，且调整最小堆
        int heapSize = arr.length;
        for (int i = arr.length - 1; i > 0; i--) {
            int t = arr[i];
            arr[i] = arr[0];
            arr[0] = t;
            this.adjMinHeap(arr, --heapSize, 0);
        }
    }

    /**
     * 构建小顶堆
     */
    private void buildMinHeap(int[] arr) {
        // 从最后一个非叶节点开始，依次调整使每个parent节点都满足最小堆特性
        for (int i = (arr.length -2) / 2; i >= 0; i--) {
            this.adjMinHeap(arr, arr.length, i);
        }
    }

    /**
     * 调整以idx为堆顶的子堆，使其满足小顶堆特性
     */
    private void adjMinHeap(int[] arr, int heapSize, int idx) {
        if (idx >= heapSize) {
            return;
        }
        // 找三个节点中最大的
        int left = (idx << 1) + 1;
        int right = left + 1;
        int min = idx;
        if (left < heapSize && arr[left] < arr[idx]) {
            min = left;
        }
        if (right < heapSize && arr[right] < arr[min]) {
            min = right;
        }
        // 最大的不为parent，则交换parent与largest，且递归largest，使其满足最小堆特性
        if (min != idx) {
            int t = arr[idx];
            arr[idx] = arr[min];
            arr[min] = t;
            this.adjMinHeap(arr, heapSize, min);
        }
    }


}
