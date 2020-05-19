package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolishNotation {
    //1.逆波兰表达式计算器，支持多位数括号，简化计算。只支持整数计算。
    //2.中缀表达式转后缀表达式
    public static void main(String[] args) {
        //给定逆波兰表达式，每个值中间用空格隔开
//        String suffixExperssion = "30 4 + 5 * 6 -";  //164
        String suffixExperssion = "4 5 * 8 - 60 + 8 2 / +";  //76
        String infixExperssion = "10+((2+3)*4)-15";
        //思路：将表达式存入一个List中，然后遍历list利用栈进行计算
        List<String> suffixList = getSuffixList(suffixExperssion);
        //先将中缀表达式转化为中缀list
        List<String> infixList = getInfixList(infixExperssion);
        System.out.println("中缀字符串转化为list：" + infixList);
        //中缀list转化后缀list
        List<String> infix2suffix = praseSuffixList(infixList);
        System.out.println("后缀表达式计算结果为："+cal(suffixList));
        System.out.println("中缀转化后缀计算结果为："+cal(infix2suffix));
    }

    //中缀list转化后缀list
    public static List<String> praseSuffixList(List<String> infixList) {
        //第一个栈存放操作符
        Stack<String> operStack = new Stack<>();
        //第二个中间结果用list存放
        List<String> midList = new ArrayList<>();
        for (String s : infixList) {
            //如果是数，加入S2
            if (s.matches("\\d+")) {
                midList.add(s);
            } else if (s.equals("(")) {
                operStack.push(s);
            } else if (s.equals(")")) {
                //如果是右括号，逐个取出S1中的符号加入S2中。直到取到左括号
                while (!operStack.peek().equals("(")) {
                    midList.add(operStack.pop());
                }
                //如果是左括号，弹出左括号
                operStack.pop();
            } else {
                //如果是操作符
                //如果当前操作符的优先级小于等于S1栈顶操作符的优先级,将S1顶的操作符弹出到list中，再次弹出S1栈顶继续比较
                while (operStack.size() > 0 && Operation.priority(s) <= Operation.priority(operStack.peek())) {
                    midList.add(operStack.pop());
                }
                //否则直接压入S1中
                operStack.add(s);
            }
        }
        //将S1中剩余的操作符弹出加入list中
        while (operStack.size() > 0) {
            midList.add(operStack.pop());
        }
        return midList;
    }

    //中缀表达式字符串转化为list
    public static List<String> getInfixList(String infix) {
        int index = 0;
        String longNum = ""; //多位整数拼接
        char ch = ' ';//每遍历一个字符存入ch中
        List<String> list = new ArrayList<>();
        do {
            //如果不是数，直接放入list中
            if ((ch = infix.charAt(index)) < 48 || (ch = infix.charAt(index)) > 57) {
                list.add(String.valueOf(ch));
                index++;
            } else {
                //考虑多位数拼接，
                while (index < infix.length() && ((ch = infix.charAt(index))>=48 && (ch = infix.charAt(index))<=57 )) {
                    longNum += ch;
                    index++;
                }
                list.add(longNum);
                //重置
                longNum = "";
            }
        } while (index < infix.length());
        return list;
    }

    //后缀转化表达式转为list
    public static List<String> getSuffixList(String exp) {
        List<String> suffixList = new ArrayList<>();
        String[] strings = exp.split(" ");
        for (String s : strings) {
            suffixList.add(s);
        }
//        List.of(strings);
        return suffixList;
    }

    public static int cal(List<String> list) {
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            //如果是数字直接放入栈
            if (s.matches("\\d+")) {
                stack.push(s);
            } else {
                num2 = Integer.parseInt(stack.pop());
                num1 = Integer.parseInt(stack.pop());
                switch (s) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("操作符有误！");
                }
                stack.push(String.valueOf(res));
            }
        }
        return res;
    }

}

//编写一个类可以比较操作符之间的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int priority(String oper) {
        int res = 0;
        switch (oper) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                //遇到左括号
                System.out.println("遇到左括号(");
        }
        return res;
    }
}
