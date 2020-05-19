package sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {2, 3, 8, 9, 2, 5, 4, 2, 1, 11, -1, 16};
        MergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    //合并分组
    public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[arr.length]; //中间数组
        int i = left; //左边数组的起始索引
        int j = mid + 1; //右边数组的起始索引
        int t = left; //中间数组的索引

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {  //哪边小哪边数进入中间数组
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        //遍历完后，把剩余左右两边的数组按顺序加入中间数组
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        //把临时数组赋给原数组，此时已经排好序
        for (int k = left; k <= right ; k++) {
            arr[k] = temp[k];
        }
    }

    //先分组，再合并
    public static void MergeSort(int[] arr, int start, int end) {
        if (start < end) { //当每组只有一个数的时候退出递归
            int mid = (start + end) / 2;
            //向左递归
            MergeSort(arr,start,mid);
            //向右递归
            MergeSort(arr, mid + 1, end);
            //合并
            merge(arr,start,mid,end);
        }
    }
}
