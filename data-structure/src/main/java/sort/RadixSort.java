package sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, 2, 11, 1,-3,-5,-9,0};
        radixSort2(arr);
        System.out.println("排序后" + Arrays.toString(arr));

    }

    //包含负数排序
    public static void radixSort2(int[] arr) {
        //考虑到有负数，找出最大最小值，比较位数较大的，求出需要计算的轮数
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        max = Math.max(max, -min);
        int count = 0;  //轮数
        int base = 10;  //表示10进制
        while (max != 0) {  //计算出进行的轮数
            max /= base;
            count++;
        }

        int bucketNum = base * 2 - 1;  //需要多少个桶

        int[][] bucket = new int[bucketNum][arr.length];  //需要19个桶，0-8号桶表示-9到-1,9道18号桶表示0-9，每个桶最多存arr.length个元素
        int[] bucketElementCount = new int[bucketNum];   //每个桶里面存的数的个数

        for (int i = 0, n = 1; i < count; i++, n *= 10) {  //控制轮数，每次n乘上10
            int index = 0;
            for (int j = 0; j < arr.length; j++) {
                //计算出每个数的个位数值
                int value = arr[j] / n % 10;
                value = value + base -1; //下标不能有负数 再原来的下标基础上加上进制-1
                //放到对应的桶中
                bucket[value][bucketElementCount[value]] = arr[j];
                bucketElementCount[value]++;
            }
            //遍历所有的桶，按顺序将元素取出，放回原数组
            for (int k = 0; k < bucket.length; k++) {
                if (bucketElementCount[k] != 0) {  //如果桶不为空
                    //按顺序将值放回原数组
                    for (int l = 0; l < bucketElementCount[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCount[k] = 0; //将桶的计数器清空
            }
        }
    }

    //只支持正数排序
    public static void radixSort(int[] arr) {

        //先算出数组最大数的位数决定需要几轮
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int count = (max + "").length(); //计算轮数
        int[][] bucket = new int[10][arr.length]; //定义10个桶，每个桶的大小最大为数组长度
        int[] bucketElementCount = new int[10]; //定义一个一维数组，表示每个桶里加入的元素个数
        for (int i = 0, n = 1; i < count; i++, n *= 10) {
            int index =0 ; //原来数组的索引
            for (int j = 0; j < arr.length; j++) {
                int value = arr[j] /n % 10; //算出个位数
                //放到对应的桶中
                bucket[value][bucketElementCount[value]] = arr[j];
                bucketElementCount[value]++;
            }
            //遍历所有的桶，按顺序将元素取出，放回原数组
            for (int k = 0; k < bucket.length; k++) {
                if (bucketElementCount[k] != 0) {  //如果桶不为空
                    //按顺序将值放回原数组
                    for (int l = 0; l < bucketElementCount[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCount[k] = 0; //将桶的计数器清空
            }
        }

        /*//第一轮，先把原来数组中的数按个位数字放入对应的桶中
        for (int j = 0; j < arr.length; j++) {
            int value = arr[j]  % 10; //算出个位数
            //放到对应的桶中
            bucket[value][bucketElementCount[value]] = arr[j];
            bucketElementCount[value]++;
        }
        //遍历所有的桶，按顺序将元素取出，放回原数组
        for (int k = 0; k < bucket.length; k++) {
            if (bucketElementCount[k] != 0) {  //如果桶不为空
                //按顺序将值放回原数组
                for (int l = 0; l < bucketElementCount[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
            }
            bucketElementCount[k] = 0; //将桶的计数器清空
        }

        System.out.println("第一轮后" + Arrays.toString(arr));

        //第二轮
        index = 0;
        for (int j = 0; j < arr.length; j++) {
            int value = arr[j] /10 % 10; //算出十位数
            //放到对应的桶中
            bucket[value][bucketElementCount[value]] = arr[j];
            bucketElementCount[value]++;
        }
        //遍历所有的桶，按顺序将元素取出，放回原数组
        for (int k = 0; k < bucket.length; k++) {
            if (bucketElementCount[k] != 0) {  //如果桶不为空
                //按顺序将值放回原数组
                for (int l = 0; l < bucketElementCount[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
            }
            bucketElementCount[k] = 0; //将桶的计数器清空
        }

        System.out.println("第二轮后" + Arrays.toString(arr));

        //第三轮
        index = 0;
        for (int j = 0; j < arr.length; j++) {
            int value = arr[j] /10/10 % 10; //算出十位数
            //放到对应的桶中
            bucket[value][bucketElementCount[value]] = arr[j];
            bucketElementCount[value]++;
        }
        //遍历所有的桶，按顺序将元素取出，放回原数组
        for (int k = 0; k < bucket.length; k++) {
            if (bucketElementCount[k] != 0) {  //如果桶不为空
                //按顺序将值放回原数组
                for (int l = 0; l < bucketElementCount[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
            }
            bucketElementCount[k] = 0; //将桶的计数器清空
        }

        System.out.println("第三轮后" + Arrays.toString(arr));*/
    }
}
