package sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
        /*int[] arr = {2, 3, 8, 9, 2, 5, 4, 2, 1, 11, -1, 16};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));*/
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = simpleDateFormat.format(date);
        System.out.println("排序前时间：" + str);

        quickSort(arr, 0, arr.length - 1);

        Date date1 = new Date();
        String str1 = simpleDateFormat.format(date1);
        System.out.println("排序后时间："+ str1);
        /*System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));*/
    }

    public static void quickSort(int[] arr, int left, int right) {
        int i, j, temp, t; //i表示左边扫描，j表示右边扫描，temp为基准点，t为中间变量用于交换
        if (left > right) { //用于结束递归，当基准点归位后，调用该方法就时left会大于right
            return;
        }
        i = left;
        j = right;
        temp = arr[left]; //基准点取每组第一个数
        while (i < j) {
            //先让J从右往左扫描，当发现比基准点小的数停止
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //再让i从左往右扫描，当发现比基准点大的数停止
            while (temp >= arr[i] && i < j) {
                i++;
            }
            if (i < j) { //交换
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;

            }
        }
        //当这个while结束后，j会等于i，此时这个位置就是基准点应该在的位置，
        // 此位置左边都比基准点小，右边都比基准点大，将基准点与该点交换，继续递归
        arr[left] = arr[i];
        arr[i] = temp;
        //基准点左边递归
        quickSort(arr, left, j - 1);
        //右边
        quickSort(arr, j + 1, right);
    }
}
