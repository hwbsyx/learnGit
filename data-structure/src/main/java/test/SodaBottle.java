package test;

public class SodaBottle {
    //
    public static void main(String[] args) {
        int buyBottles = 16;
        int bottles = getBottles(buyBottles);
        System.out.println("空瓶子换了" + bottles + "瓶" + "总共喝了" + (bottles + buyBottles) + "瓶");
    }

    /**
     * 3个空瓶子换一瓶汽水，现在买了n瓶汽水，总共喝了多少瓶
     * @param n 买的汽水
     * @return 空瓶子换的汽水数目
     */
    public static int getBottles(int n) {
        int count = 0; //空瓶子换的汽水数
        while (n >= 3) {
            count += n / 3;
            n = n / 3;
        }
        return count;
    }
}
