package sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3, -1, 10, -2, 8};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = simpleDateFormat.format(date);

        System.out.println("排序前时间："+str);
//        System.out.println(Arrays.toString(arr));

        bubbleSort(arr);
        Date date1 = new Date();
        String str1 = simpleDateFormat.format(date1);
        System.out.println("排序后时间："+ str1);
//        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        //时间复杂度O(n^2)
        int temp = 0;
        boolean flag = false; //判断是否进行过交换位置
        //外层循环控制要进行几轮比较，
        for (int i = 0; i < arr.length - 1; i++) {
            //内层循环控制每轮进行几次比较
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;   //进行过交换
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                //没进行交换
                break;
            } else {
                flag = false; //进行过交换，重置flag进行下一轮比较
            }
        }
    }
}
