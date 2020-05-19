package stack;

public class Calculator {
    //中缀表达式计算器，加减乘除，多位数，不带括号
    public static void main(String[] args) {
        //中缀表达式
//        String experssion = "7*2*2-5+1-5+3-4";//18
        String experssion = "3+4*3-20";
        //数栈和操作符栈
        ArrayStack2<Integer> numStack = new ArrayStack2<>(10);
//        ArrayStack2<Integer> operStack = new ArrayStack2<>(10);
        ArrayStack2<Character> operStack = new ArrayStack2<>(10);
        int index = 0; //指针
        char ch; //扫描的每个字符
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        String longNum = ""; //拼接多位数
        while (true) {
            //依次扫描每个字符
            ch = experssion.substring(index, index + 1).charAt(0);
            //判断ch是什么
            if (operStack.isOper(ch)) { //如果是操作符
                //如果不为空
                if (!operStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较,
                    // 如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数,
                    // 在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈，
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
//                        operStack.push((int) ch);  //如果是integer类型的栈
                        operStack.push(ch);
                    } else {
                        // 如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
//                        operStack.push((int) ch);
                        operStack.push(ch);
                    }
                } else {
                    //如果发现当前的符号栈为 空，就直接入栈
                    operStack.push(ch);
                }
            } else {
                //如果是数，考虑多位数拼接
                longNum += ch;
                //如果是最后一位
                if (index == experssion.length() - 1) {
                    numStack.push(Integer.valueOf(longNum));
                } else {
                    //如果下一位是操作符
                    if ((operStack.isOper(experssion.substring(index + 1, index + 2).charAt(0)))) {

                        numStack.push(Integer.valueOf(longNum));
                        //清空longNum
                        longNum = "";
                    }
                }
            }
            index++;
            if (index >= experssion.length()) {
                break;
            }
        }
        //依次弹出符号和数进行计算
        while (!operStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        int res2 = numStack.pop();
        System.out.printf("%s=%d", experssion, res2);
    }
}

class ArrayStack2<T> {
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
            System.out.printf("stack[%d]=%s \n", top, stack[top]);
            top--;
        }
    }

    //判断是不是操作符
    public boolean isOper(int oper) {
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    //比较操作符优先级
    public int priority(int oper) {
        if (oper == '+' || oper == '-') {
            return 0;
        } else if (oper == '*' || oper == '/') {
            return 1;
        } else { //不是+ - * /
            return -1;
        }
    }

    //两数进行计算
    public int cal(int num1, int num2, int oper) {
        int res;//计算结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                throw new RuntimeException("操作符有误");
        }
        return res;
    }

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        this.stack = (T[]) new Object[maxSize];
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
