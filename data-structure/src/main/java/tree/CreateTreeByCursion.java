package tree;

import java.util.LinkedList;

/**
 * @description: 利用递归方法构建二叉树
 * @author: He
 * @create: 2020-04-14 23:37
 **/

public class CreateTreeByCursion {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        LinkedList<Node> list = new LinkedList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        createTree(list);
        preOrder(node1);


    }

    public static Node createTree(LinkedList<Node> list) {
        Node node = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        Node curNode = list.removeFirst();
        if (curNode != null) {
            node = curNode;
            node.left = createTree(list);
            node.right = createTree(list);
        }
        return node;

    }

    public static void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}