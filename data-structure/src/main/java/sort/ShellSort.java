package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {9, 8, 6, 7, 4, 5, 2, 1, 3, 0};

        int[] arr = new int[8];
        for (int i = 0; i < 8; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = simpleDateFormat.format(date);
        System.out.println("排序前时间：" + str);
//        shellSort(arr);  //交换法
        shellSort2(arr);   //移位法

        Date date1 = new Date();
        String str1 = simpleDateFormat.format(date1);
        System.out.println("排序后时间："+ str1);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {

        int temp = 0;
        int count = 0; //记录轮数
        for (int grap = arr.length/2; grap >0 ; grap/=2) {
            for (int i = grap; i < arr.length; i++) {
                //此时分为 5组，每组增量为5，对每组进行插入排序,插入下标为i-5,下一个插入下标为第一个下标减去增量5，即j -= 5
                for (int j = i - grap; j >= 0; j -= grap) {
                    if (arr[j + grap] < arr[j]) { //后面的小于前面的,交换
                        temp = arr[j +grap];
                        arr[j +grap] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
           /* System.out.println("第" + (++count) + "轮后的结果");
            System.out.println(Arrays.toString(arr));*/
        }

        /*//第一轮排序,增量为10/2 =5,
        for (int i = 10 / 2; i < arr.length; i++) {
            //此时分为 5组，每组增量为5，对每组进行插入排序,插入下标为i-5,下一个插入下标为第一个下标减去增量5，即j -= 5
            for (int j = i - 5; j >= 0; j -= 5) {
                if (arr[j + 5] < arr[j]) { //后面的小于前面的,交换
                    temp = arr[j +5];
                    arr[j +5] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        //第二轮排序 增量为 5/2 =2
        for (int i = 5 / 2; i < arr.length; i++) {
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j +2] < arr[j]) { //后面的大于前面的,交换
                    temp = arr[j +2];
                    arr[j+2] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        //第三轮 增量为 2/2 =1
        for (int i = 2 / 2; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j+1] < arr[j]) { //后面的大于前面的,交换
                    temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }*/
    }

    //移位法，找到对应的位置后再移动，而不是发现小于就交换位置
    public static void shellSort2(int[] arr) {
        int temp = 0;
        int j = 0;
        for (int grap = arr.length / 2; grap > 0; grap /= 2) {
            for (int i = grap; i < arr.length; i++) {
                j = i; //记录插入索引
                temp = arr[j]; //记录插入值
                while (j - grap >= 0 && temp < arr[j - grap]) {
                    arr[j] = arr[j - grap]; //将大数移位到后面
                    j -= grap;
                }
                //当while循环结束后，就找到了temp应该排在的位置
                arr[j] = temp;
                /*if (arr[j] < arr[j - grap]) { //后面的比前面的小
                }*/
            }
        }
    }
}
