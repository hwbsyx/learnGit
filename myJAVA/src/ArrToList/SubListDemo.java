package ArrToList;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: ArrayList的sublist（）方法
 * @author: He
 * @create: 2020-04-15 17:24
 **/

public class SubListDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<Integer> subList = list.subList(0, 1);
        ArrayList<Integer> cast = (ArrayList<Integer>) subList;

    }
}
