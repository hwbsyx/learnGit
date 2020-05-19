package tree;

public class ArrBinaryTreeDemo {
    //顺序存储二叉树，二叉树和数组可以相互转换,
    //顺序存储二叉树只考虑完全二叉树
    // 若当前节点索引为n，满足当前节点的左子节点为2*n+1
    //右子节点为2*n+2，
    // 当前节点的父节点为（n-1）/2
    //根节点索引为0
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();

    }
}

class ArrBinaryTree {
    private int[] arr; //存储数据的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载前序遍历，默认从根节点开始遍历
    public void preOrder() {
        this.preOrder(0);
    }

    /**
     * 前序遍历
     * @param index 给定索引，从当前索引开始遍历
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法前序遍历");
        }
        System.out.println(arr[index]);
        //向左递归
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        //向右递归
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}
