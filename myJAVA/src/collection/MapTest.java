package collection;

import java.util.HashMap;

public class MapTest {
    public static void main(String[] args) {
        var map = new HashMap();
        map.put("疯狂Java讲义",109);
        map.put("疯狂IOS讲义",10);
        map.put("疯狂Ajax讲义",79);
        map.put("轻量级Java EE企业应用实战",99);

//        放入重复的key会覆盖原来的value，put方法会返回被覆盖的value
        System.out.println(map.put("疯狂IOS讲义",99));
        System.out.println(map);


        for (var key : map.keySet()){
            System.out.println(key + "===" +map.get(key) );
        }

    }
}
