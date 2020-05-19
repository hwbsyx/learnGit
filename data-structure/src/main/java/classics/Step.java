package classics;

public class Step {
    /**
     * 有一个n阶台阶，一次可以走1阶或者2阶，走到n阶总共有几种走法
     */
    public static void main(String[] args) {
        System.out.println(cal1(4));
    }
    //当n=1时，有1种走法，当n=2时，有2种走法，一次2步或者2次一步；当n=3时，有f(1)+f(2)种走法，
    // f(1)表示到第一阶的走法数目，f(2)表示到第二节的走法数目.
    //f(n)=f(n-1)+f(n-2)
    private static int cal(int n){
        if (n < 1) {
            System.out.println("台阶数不能小于1");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return cal(n - 1) + cal(n - 2);
    }

    //递归过程中会计算多次重复问题。例如f（5）=f(4)+f(3).f(6)=f(5)+f(4)。计算了2次f（4)。
    // 如果把计算的值保存起来。需要的时候提取出来。采用数组arr[n]来表示f(n)，如果已经存在。就直接调用。
    //不存在就返回0
    private static int cal1(int n){
        int[] arr = new int[n+1];
        if (n < 1) {
            System.out.println("台阶数不能小于1");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        if (arr[n] != 0) {  //如果不为0说明已经存在，直接调用，
            return arr[n];
        } else {     //如果等于0.调用递归计算出值存入数组
            arr[n] = cal1(n - 1) + cal1(n - 2);
            return arr[n];
        }
    }

    //利用循环，走到第一阶台阶有1种走法，走到第二节台阶有2种走法，从第三节台阶开始，
    // 走法为走到第二节的走法加上走到第一阶的走法
    private static int cal2(int n) {
        if (n < 1) {
            System.out.println("台阶数不能小于1！");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int two = 1;  //表示走到第一阶台阶有1种走法
        int one = 2;  //表示走到第二节台阶有2中走法
        int sum = 0;
        for (int i = 3; i <=n ; i++) {
            sum = two + one;
            two = one;  //求下一次走法的时候，倒数第二节变倒数第一阶
            one = sum;  //倒数第一阶变当前阶。
        }
        return sum;
    }
}
