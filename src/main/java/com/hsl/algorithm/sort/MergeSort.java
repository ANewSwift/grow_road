package com.hsl.algorithm.sort;

import com.sun.scenario.effect.Merge;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        MergeSort main = new MergeSort();
//        int[] arr = new int[]{1,3,4,1, 2, 5};
//        main.merge(arr,0, 2, 5);
//        Arrays.stream(arr).forEach(System.out::println);
        int[] arr = new int[]{3, 5, 4, 1, 2, 6};
        main.mergeSort(arr, 0, arr.length-1);
        Arrays.stream(arr).forEach(System.out::println);
    }
    public void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = (r + l) / 2;
        this.mergeSort(arr, l, mid);
        this.mergeSort(arr, mid+1, r);
        this.merge(arr, l, mid, r);
    }

    /**
     * 合并两个有序数组
     */
    private void merge(int[] arr, int l, int mid, int r) {
        int[] arrL = new int[mid - l + 2];
        int[] arrR = new int[r - mid + 1];
        for (int i = 0; i < arrL.length - 1; i++) {
            arrL[i] = arr[i+l];
        }
        arrL[arrL.length - 1] = Integer.MAX_VALUE;
        for (int i = 0; i < arrR.length - 1; i++) {
            arrR[i] = arr[i+mid+1];
        }
        arrR[arrR.length - 1] = Integer.MAX_VALUE;
        int p1 = 0, p2 = 0;
        for (int i = l; i <= r; i++) {
            if (arrL[p1] <= arrR[p2]) {
                arr[i] = arrL[p1];
                p1++;
            } else {
                arr[i] = arrR[p2];
                p2++;
            }
        }
    }
}
