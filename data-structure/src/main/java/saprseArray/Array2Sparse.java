package saprseArray;

import java.io.*;

/*
 *将一个棋盘用二维数组表示并转化为稀疏数组,将系数数组存盘，
 * 读取稀疏数组，并转化为二维数组
 * */
public class Array2Sparse {
    public static void main(String[] args) throws IOException {
        //初始化棋盘数组
        int chessArray[][] = new int[11][11];
        //有效数据的个数
        int sum = 0;
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[4][5] = 2;
        for (int row[] : chessArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
                if (data != 0) {
                    //统计有效数据的个数
                    sum++;
                }
            }
            System.out.println();
        }

        System.out.println("有效数据个数为" + sum);

        //转化为稀疏数组
        int sparseArray[][] = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        //遍历二维数组将非0值赋值给稀疏数组
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }

        for (int[] row : sparseArray) {
            System.out.printf("%d\t%d\t%d\t\n",row[0],row[1],row[2]);
        }
        int sparseArr2[][] = new int[sparseArray.length][sparseArray[0].length];

        //将稀疏数组写入saprse.txt文件中
        File file = new File("saprse.txt");
        FileWriter fileWriter = new FileWriter(file);
        for (int[] ints : sparseArray) {
            for (int data : ints) {
                fileWriter.write(data+"\t");
            }
            fileWriter.write("\r\n");
        }
        fileWriter.close();
        //读取文件
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        int row =0;//记录行数

        while ((line = bufferedReader.readLine()) != null) {
            String temp[] = line.split("\t");
            for (int i =0;i<temp.length;i++) {
                sparseArr2[row][i] = Integer.parseInt(temp[i]);
            }
            row++;
        }
        bufferedReader.close();

        System.out.println("读取的稀疏数组为:");
        for (int[] ints : sparseArr2) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }

        //将稀疏数组转化为二维数组
        int chessArr2[][] = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        for (int i = 1; i <sparseArr2.length ; i++) {
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }
        System.out.println("稀疏数组转化的二维数组为：");
        for (int rows[] : chessArray) {
            for (int data : rows) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }

}
