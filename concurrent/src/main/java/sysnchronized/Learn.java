package sysnchronized;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;

public class Learn {
    public static void main(String[] args) {
        ArrayList a = new ArrayList();
        System.out.println(a.hashCode());
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
