import java.io.*;
import java.util.Scanner;

public class Login {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        while(true){
            System.out.println("请选择功能： A(注册)   B(登陆)");
            String option =  scanner.next();
            if("a".equalsIgnoreCase(option)){
                //注册
                reg();

            }else if("b".equalsIgnoreCase(option)){
                //登陆
                login();

            }else{
                System.out.println("你的输入有误,请重新输入...");
            }
        }
    }


    //登陆
    public static void login() throws IOException{
        System.out.println("请输入用户名：");
        String userName = scanner.next();
        System.out.println("请 输入密码：");
        String password = scanner.next();
        String info = userName+" "+ password;
        //读取文件的信息，查看是否有该用户的信息存在，如果存在则登陆成功。
        //建立数据的输入通道
        //建立缓冲输入字符流
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\users.txt"));
        String line = null;

        boolean isLogin = false; // 用于记录是否登陆成功的标识， 默认是登陆失败的。
        //不断的读取文件的内容
        while((line = bufferedReader.readLine())!=null){
            if(info.equals(line)){
                isLogin = true;
                break;
            }
        }

        if(isLogin){
            System.out.println("欢迎"+userName+"登陆成功...");
        }else{
            System.out.println("不存在该用户信息，请注册!!");
        }



    }




    //注册
    public static void reg() throws IOException {
        System.out.println("请输入用户名：");
        String userName = scanner.next();
        System.out.println("请 输入密码：");
        String password = scanner.next();
        String info = userName+" "+ password;
        //把用户的注册的信息写到文件上
        File file = new File("D:\\users.txt");
        FileWriter fileWriter = new FileWriter(file,true);
        //建立缓冲输出字符流
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        //把用户信息写出

        bufferedWriter.write(info);
        bufferedWriter.newLine();
        //关闭资源
        bufferedWriter.close();

    }
}
