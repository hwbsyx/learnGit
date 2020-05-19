package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {2, 3, 8, 9, 2, 5, 4, 2, 1, 11, -1, 16,2,2};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
//        RadixSort.radixSort2(arr);
        System.out.println("排序后的数组为" +Arrays.toString(arr));
        List<Integer> resList = binarySearch(arr, 0, arr.length - 1, 2);
        System.out.println("目标值所在的索引为" + resList);
    }

    public static List<Integer> binarySearch(int[] arr,int left,int right, int findValue) {

        if (left > right || findValue < arr[left] || findValue > arr[right]) { //如果没找到返回空集合
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        if (findValue < arr[mid]) {
            //目标在左边
            return binarySearch(arr, left, mid - 1, findValue);
        } else if (findValue > arr[mid]) {
            //目标在右边
            return binarySearch(arr, mid + 1, right, findValue);
        } else {
            //找到了 findValue = arr[mid]
            List<Integer> resList = new ArrayList<>();
            int temp = mid - 1;
            //向左继续查找
            while (true) {
                if (temp < 0 || arr[temp] != findValue) { //如果左边的数不等于查找值时退出
                    break;
                }
                resList.add(temp--);
            }
            resList.add(mid); //左边全加入后，中间再加入
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
