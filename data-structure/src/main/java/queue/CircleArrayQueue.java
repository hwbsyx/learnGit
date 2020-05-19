package queue;

import java.util.Scanner;

public class CircleArrayQueue {
    public static void main(String[] args) {
        CircleQueue queue = new CircleQueue(4);//实际只能存3个元素，保留一位做循环
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        char key;
        while (loop) {
            System.out.println("a(addQueue) 添加元素");
            System.out.println("g(getQueue) 取出元素");
            System.out.println("s(showQueue) 显示队列");
            System.out.println("h(headQueue) 查看队列第一个元素");
            System.out.println("e(exit) 退出程序");
            key = scanner.next().charAt(0);
            switch (key) {
                case 'a':
                    System.out.println("请输出要添加的元素:");
                    queue.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    try {
                        int value = queue.getQueue();
                        System.out.printf("取出的元素是%d\n", value);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 's':
                    queue.showQueue();
                    break;
                case 'h':
                    try {
                        System.out.printf("第一个元素是%d\n", queue.headQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序已经退出!");
    }

}

class CircleQueue {
    private int maxSize; //队列最大容量
    //front只想队列第一个元素,初始值为0
    private int front; //队列头部
    //rear指向队列最后一个元素后的一个位置,初始值为0
    private int rear;  //队列尾部
    private int arr[];//构成队列的数组

    //初始化循环队列
    public CircleQueue(int maxArr) {
        this.maxSize = maxArr;
        this.arr = new int[maxArr];
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //判断队列是否满,尾索引的下一个为头索引时判断为队列满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //添加元素
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满，无法添加元素");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //取出元素
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法取出数据");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //查看第一个元素
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空!");
        }
        return arr[front];
    }

    //显示队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据。");
            return;
        }
        //遍历,从fornt开始，遍历有效个数
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //计算有效个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }
}
