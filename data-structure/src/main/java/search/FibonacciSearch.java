package search;

import sort.RadixSort;

import java.util.Arrays;

public class FibonacciSearch {
//    static int maxSize = 20; //斐波那契数列的元素个数
    public static void main(String[] args) {
        int[] arr = {2, 3, 8, 9, 2};
        RadixSort.radixSort2(arr);
        System.out.println("排序后的数组为" +Arrays.toString(arr));
        int result = fibSearch(arr,2);
        System.out.println("目标值所在的索引为" + result);
    }

    public static int[] fib(int maxSize) { //maxSize为斐波那契数列的元素个数
        int[] fib = new int[maxSize];
        fib[0]=1;
        fib[1]=1;
        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    public static int fibSearch(int[] arr, int findValue) {
        //mid=low+F(k-1)-1
        int low = 0;
        int high = arr.length - 1;
        int k = 0; //斐波那契数列下标
        int mid = 0;
        int[] fib = fib(20);
        while (high > fib[k]-1) { //查找的数组长度要满足n<=F[K]-1
            k++;
        }
        int[] temp = Arrays.copyOf(arr, fib[k]); //将原来的数组扩容，扩容后用0补齐
        for (int i = arr.length -1; i < temp.length; i++) {
            temp[i] = arr[high];  //将扩容的值用原数组最后一位补齐
        }
        //开始查找
        while (low <= high) {
            mid = low + fib[k - 1] - 1;
            if (findValue < arr[mid]) {  //在f[k-1]这一段
                high = mid - 1;
                k = k - 1;
            } else if (findValue > arr[mid]) { //在f[k-2]这一段
                low = mid + 1;
                k = k - 2;
            } else {
                //findValue = mid
                if (mid <= high) {  //找到的mid还在原数组范围内，
                    return mid;
                } else { //mid已经超过原数组大小，由于扩容值都等于arr[high]，直接返回high即可
                    return high;
                }
            }
        }
        return -1; //没有找到
    }
}
