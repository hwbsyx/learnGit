package huffumancode;

public class BinaryTest {
    public static void main(String[] args) {
        byte b = -14;  // 0000 1110
        int a = b;
        a |= 256;
        String s = Integer.toBinaryString(a);
        System.out.println(s);
        String c = s.substring(s.length()-8);
        byte i = (byte) Integer.parseInt(c, 2);
        System.out.println(i);
    }
}
