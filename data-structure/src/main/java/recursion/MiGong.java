package recursion;

public class MiGong {
    public static void main(String[] args) {
        //建立二维数组模拟迷宫
        int row = 8;
        int column = 7;
        int[][] map = new int[row][column];
        //把第一行和最后一行设置为墙壁，墙壁为1，没走过的路设为0，走过的设为2，不能走的设置为3
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //第一列和最后一列设为墙壁
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //第4行第2,3列都设置为障碍
        map[3][1] = 1;
        map[3][2] = 1;

        //打印地图
        System.out.println("初始化迷宫为:");
        printMap(map,row,column);

        setWay(map, 1, 1);
        System.out.println("找到的通路为:");
        printMap(map,row,column);

    }

    private static void printMap(int[][] map,int row,int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * map是迷宫地图，i,j表示起始位置的坐标
     * 如果小球能到map[6][5]说明通路能找到
     * 约定：当map[i][j]为0表示该点没有走过，为1表示墙壁，为2表示通路可以走，为3表示该点已经走过但是走不通
     * 走迷宫时需要确定一个策略，下->右->上->左，如果都走不通，就回溯
     *
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay(int[][] map,int i,int j) {
        //返回条件
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {//如果这个点还没走过，按照策略下 右 上 左走
                map[i][j] = 2;//假设改点可以走
                if (setWay(map, i + 1, j)) { //向下走
                    return true;
                } else if (setWay(map, i, j + 1)) { //右
                    return true;
                } else if (setWay(map, i - 1, j)) { //上
                    return true;
                } else if (setWay(map, i, j - 1)) { //左
                    return true;
                } else { //说明该点是死路，走不通，设置为3
                    map[i][j] = 3;
                    return false;
                }
            } else { //如果该点不为0，可能是1 2 3
                return false;
            }
        }
    }
}
