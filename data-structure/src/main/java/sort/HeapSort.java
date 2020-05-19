package sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9,-1,20,35,-99};
        System.out.println("排序前" + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后" + Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int temp = 0; //临时变量用于交换
        //先将数组调整为大顶堆，从下往上,找到第最下面一个非叶子节点索引为length/2-1
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr,i,arr.length);
        }
        //交换位置，将最大值与最后一个子节点交换，j表示需要调整的数组长度
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);  //交换一次位置后可以从上往下进行调整大顶堆，此时需要调整的长度为j
        }
    }

    /**
     * 将数组调整为大顶堆，每个节点的值大于等于子节点的值
     *
     * @param arr 要调整的数组
     * @param i   要调整的节点所在数组的索引
     * @param length  调整的长度
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];  // 将叶子节点的值保存到临时变量
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) { //从当前节点的左子节点开始，
            if (k + 1 < length && arr[k] < arr[k + 1]) { //如果左子节点小于右子节点，将指针指向右子节点
                k++;
            }
            if (temp < arr[k]) { //当前节点值小于子节点值，将子节点赋值给当前节点，不交换
                arr[i] = arr[k];
                i = k;
            } else {
                break;  //如果当前节点不小于子节点，直接退出调整，如果是从上往下调整，
                        // 可能会出现当前节点虽然不小于自己的子节点，但是会小于子节点的子节点，
                        // 所以需要从下往上进行顶堆调整，这样可以保证子节点以下的节点不可能比当前节点更大
            }
        }
        arr[i] = temp;// 此时完成交换位置。
    }
}
