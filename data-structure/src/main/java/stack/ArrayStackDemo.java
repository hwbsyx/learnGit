package stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(3);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("请输入 list（遍历）");
            System.out.println("请输入 push（入栈）");
            System.out.println("请输入 pop（出栈）");
            System.out.println("请输入 peek（查看栈顶）");
            System.out.println("请输入 exit（退出）");
            String next = scanner.next();
            switch (next) {
                case "list":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入要入栈的值：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        Integer pop = stack.pop();
                        System.out.println("出栈的值为" + pop);
                    } catch (Exception e) {
                        throw new RuntimeException("栈空！");
                    }
                    break;
                case "peek":
                    Integer peek = stack.peek();
                    System.out.println("栈顶的值为" + peek);
                    break;
                case "exit":
                    loop = false;
                    break;
                default:
                    System.out.println("输入参数有误，请重新输入");
                    break;
            }
        }
        System.out.println("程序已经退出");

    }
}

class ArrayStack<T> {
    private int maxSize;
    private T[] stack;
    private int top = -1;

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //查看栈顶
    public T peek() {
        if (isEmpty()) {
            System.out.println("栈已空");
            return null;
        }
        return stack[top];
    }
    //入栈
    public void push(T element) {
        if (isFull()) {
            System.out.println("栈已满，无法入栈");
            return;
        }
        top++;
        stack[top] = element;
    }

    //出栈
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空！");
        }
        return stack[top--];
    }

    //遍历
    public void list() {
        if (isEmpty()) {
            System.out.println("栈已空！");
            return;
        }
        while (top != -1) {
            System.out.printf("stack[%d]=%s \n",top,stack[top]);
            top--;
        }
    }

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack =(T[]) new Object[maxSize];
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public T[] getStack() {
        return stack;
    }

    public void setStack(T[] stack) {
        this.stack = stack;
    }
}