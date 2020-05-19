package sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -2, 11, -1};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        insertSort(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        //i=0默认为有序数组，从i=1开始遍历，每轮插入一个数到有序数组中，一共N-1轮
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i]; //从第i个数开始作为待插入的数
            insertIndex = i - 1; //插入的起始位置为待插入数的前一个
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) { //待插入的数比有序数组中的数小，
                arr[insertIndex + 1] = arr[insertIndex]; //将大的数后移
                insertIndex--;
            }
            //当while退出后，说明插入位置已经找到insertIndex + 1
            //遍历完后，小的数归位
            if (insertIndex + 1 != i) {  //如果插入位置不是原来的位置，将插入值赋给插入位置。
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
