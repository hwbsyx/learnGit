package huffmantree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//给定数列，生成赫夫曼树
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = getHuffman(arr);
        preOrder(root);
    }

    //前序遍历
    public static void preOrder(Node root) {
        if (root == null) {
            System.out.println("空树，无法遍历");
        } else {
            root.preOrder();
        }
    }

    //生成赫夫曼树，并返回根节点
    public static Node getHuffman(int[] arr) {
        //将数组中的数当做结点的值，然后加入到集合中，便于排序
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        //循环操作，直到list中只剩最后一个结点,
        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);
            //得到最小的两个结点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //两个结点权重相加得到新结点权重
            Node rootNode = new Node(leftNode.getValue() + rightNode.getValue());
            rootNode.setLeft(leftNode);
            rootNode.setRight(rightNode);
            nodes.add(rootNode);
            //删除原来的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
        }
        return nodes.get(0); //最后一个节点返回，作为赫夫曼树根节点
    }
}

class Node implements Comparable<Node> {  //为了使用collections.sort()方法，需要实现该接口
    private int value;
    private Node left;
    private Node right;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node(int value) {
        this.value = value;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) { //从小到大排，
        return this.value - o.value;
    }
}
