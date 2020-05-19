package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerfectNum {
    //完全数，自身约数的和（不包括自己）等于自身的数，如6=1+2+3；
    public static void main(String[] args) {
        System.out.println(getPerfectNum(10000));
    }

    //求一个数的约数，不包括自己，返回一个集合。
    public static List<Integer> getDivisors(int n) {
        ArrayList<Integer> numList = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                numList.add(i);
            }
        }
        return numList;
    }

    //求一个1到num以内所有的完全数，放入一个map，key为这个数，value为这个数的约数
    public static Map<Integer,List<Integer>> getPerfectNum(int num) {
        HashMap<Integer, List<Integer>> resultMap = new HashMap<>();

        for (int i = 1; i <= num; i++) {
            List<Integer> nums = getDivisors(i);
            int sum =0;
            for (Integer value : nums) {
                sum += value;
            }
            if (i == sum) {
                resultMap.put(i, nums);
            }
        }
        return resultMap;
    }
}
