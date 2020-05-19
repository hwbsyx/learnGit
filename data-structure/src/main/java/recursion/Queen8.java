package recursion;

public class Queen8 {
    int max = 8; //有几个皇后
    int[] array = new int[max]; //用一维数组表示皇后位置，index+1表示皇后所在行，value+1表示皇后所在列
    static int count = 0; //总共多少种方法

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("共计%d种", count);
    }

    //摆放皇后,n表示摆放第几个皇后
    private void check(int n) {
        if (n == max) { //摆放完成
            print();
            return;
        }
        //依次摆放皇后，看是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后放到第一列
            array[n] = i;
            //判断是否冲突
            if (judge(n)) {
                //不冲突，接着放n+1
                check(n + 1);
            }
            //如果冲突，继续执行循环，将皇后放到第二列
        }
    }

    /**判断放第N个皇后时是否与N之前的皇后位置冲突，
     *
     * array[i]  == array[n] 判断是否在同一列
     * Math.abs(n - i) == Math.abs(array[n] - array[i]) 判断是否在同一斜线上
     *
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //输出结果
    private void print() {
        count++;
        for (int i = 0; i < max; i++) {
            System.out.printf(array[i] + " ");
        }
        System.out.println();
    }
}
