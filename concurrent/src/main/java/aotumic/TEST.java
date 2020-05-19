package aotumic;

import java.io.File;

public abstract class TEST {
    public final static native int w();

    abstract double hyperbolicCosine();
    public static void main(String[] args) {
        if (new File("d://a.txt").exists()) {

            System.out.println(1);
        }
        String s1 = "a" + "b";
        String s2 = new String(s1);
        if (s1 == s2) {
            System.out.println(1);
        }
        if (s1.equals(s2)) {
            System.out.println(2);
        }
    }

    public static void TEST() {

    }

}

