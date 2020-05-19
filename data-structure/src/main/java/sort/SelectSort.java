package sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -2, 11, -1};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = simpleDateFormat.format(date);
        System.out.println("排序前时间：" + str);

        selectSort(arr);

        Date date1 = new Date();
        String str1 = simpleDateFormat.format(date1);
        System.out.println("排序后时间："+ str1);

    }

    public static void selectSort(int[] arr) {

        //第一轮将最小的值放在索引第一位置
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                //如果不是第一个数不是最小，
                if (min > arr[j]) {
                    minIndex = j; //重置最小值索引以及值
                    min = arr[j];
                }
            }
            if (minIndex != i) { //如果最小值与索引不对应，交换位置
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            /*System.out.println("第" + (i + 1) + "轮排序后");
            System.out.println(Arrays.toString(arr));*/
        }

       /* //第一轮将最小的值放在索引第一位置
        int minIndex = 0;
        int min = arr[0];
        for (int j = 1; j < arr.length ; j++) {
            //如果不是第一个数不是最小，
            if (min > arr[j]) {
                minIndex = j; //重置最小值索引以及值
                min = arr[j];
            }
        }
        if (minIndex != 0) { //如果最小值与索引不对应，交换位置
            arr[minIndex] = arr[0];
            arr[0] = min;
        }
        System.out.println("第一轮排序后");
        System.out.println(Arrays.toString(arr));*/
    }
}
