package search;

import sort.RadixSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = {2, 3, 8, 9, 2, 5, 4, 2, 1, 11, -1, 16};
        RadixSort.radixSort2(arr);
        System.out.println("排序后的数组为" + Arrays.toString(arr));
        List<Integer> resList = insertValueSearch(arr, 0, arr.length - 1, 2);
        System.out.println("目标值所在的索引为" + resList);
    }

    public static List<Integer> insertValueSearch(int[] arr, int left, int right, int findValue) {
        if (left > right || findValue < arr[left] || findValue > arr[right]) {
            return new ArrayList<>();
        }
        //必须判断findValue的范围，否则mid可能越界
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        if (findValue < arr[mid]) {
            return insertValueSearch(arr, left, mid - 1, findValue);
        } else if (findValue > arr[mid]) {
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else {
            //findValue = mid
            List<Integer> resList = new ArrayList<>();
            //向左继续查找是否还存在相同的数
            int temp = mid - 1;
            while (true) {
                if (temp < left || arr[temp] != findValue) {//越界或者不是相同的数就退出
                    break;
                }
                resList.add(temp--);
            }
            resList.add(mid);
            //向右继续查找
            temp = mid + 1;
            while (true) {
                if (temp > right || arr[temp] != findValue) {
                    break;
                }
                resList.add(temp++);
            }
            return resList;
        }
    }
}
