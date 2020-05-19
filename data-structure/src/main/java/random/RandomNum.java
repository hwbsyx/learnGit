package random;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 1.为频散曲线添加随机数
 * 2.将surfer提取的频散曲线去除Z坐标
 */
public class RandomNum {

    public static void main(String[] args) throws IOException {
//        addRandom();
        extractFv();
//        transfer();
    }

    private static void extractFv() throws IOException {
        for (int i = 371; i <= 371; i++) {
            //读取文件
            File file = new File("C:\\Users\\Administrator\\Desktop\\realdata\\BDH2016\\"+ i +"dispersion.dat");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
//        int row = 0; //记录行数
            List<String> data = new ArrayList<>();  //存放 f  vr的集合
            Double f;  //频率
            Double vr;//相速度
            String fv;


            while ((line = bufferedReader.readLine()) != null) {
                String[] temp = line.trim().split(",");  //去掉首尾的空格，以空格分出两个数//取最后一个，即第二列数
                f = Double.valueOf(temp[0]);
                vr = Double.valueOf(temp[1]);
                fv = f + "\t" + vr;
                data.add(fv);
            }
            bufferedReader.close();
            File file1 = new File("C:\\Users\\Administrator\\Desktop\\realdata\\BDH2016\\"+ i +"频散曲线.txt");
            FileWriter fileWriter = new FileWriter(file1);
            for (String value : data) {
                fileWriter.write(value + "\r\n");
            }
            fileWriter.close();

        }
    }

    private static void addRandom() throws IOException {
        for (int i = 24; i < 57; i++) {
            //读取文件
            File file = new File("D:\\program\\dispersion curve forward calculation\\2layer10m.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
//        int row = 0; //记录行数
            List<String> data = new ArrayList<>();  //存放 f  vr的集合
            Double f;  //频率
            Double vr;//相速度
            String fv;


            while ((line = bufferedReader.readLine()) != null) {
                String[] temp = line.trim().split("\\s");  //去掉首尾的空格，以空格分出两个数//取最后一个，即第二列数
                f = Double.valueOf(temp[0]);
                vr = Double.valueOf(temp[temp.length - 1]);
                vr += (Math.random() * 10);
                fv = f + "\t" + vr;
                data.add(fv);
            }
            bufferedReader.close();
            File file1 = new File("D:\\program\\dispersion curve forward calculation\\2layer10mNEW"+i+".txt");
            FileWriter fileWriter = new FileWriter(file1);
            for (String value : data) {
                fileWriter.write(value + "\r\n");
            }
            fileWriter.close();
            /*int[] randomNum = new int[45];
        for (int i = 0; i < 45; i++) {
            randomNum[i] = (int) (Math.random() * 10); //产生1-10的随机数
        }*/
            //将随机数写入
        }
    }

    public  static void transfer() throws IOException {
        File file = new File("D:\\BDH2016\\bdh378.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = null;
        List<String> data = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String value = StringUtils.substringAfterLast(line.trim(), " ");
            data.add(value);
        }
        data.remove(0);
        bufferedReader.close();
        File out = new File("D:\\BDH2016\\bdh378out.txt");
        FileWriter fileWriter = new FileWriter(out);
        for (int i = 1; i <= 1001; i++) {
            for (int j = 1; j <= 24; j++) {
                if (data.size() != 0) {
                    String s = data.remove(0);
                    fileWriter.write((s + "\t"));
                }
            }
            fileWriter.write("\r\n");
        }
        fileWriter.close();
    }
}
