import java.io.*;
import java.util.Scanner;

public class Register {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {

        while (true){
            System.out.println("请选择操作 A（注册） B（登录）");
            String result = scanner.next();
            if ("a".equalsIgnoreCase(result)){
                //注册
                reg();
            }else if ("b".equalsIgnoreCase(result)){
                //登录
                login();
            }else {
                System.out.println("输入有误，请重新输入");
            }

        }
    }


    public static void reg() throws IOException {
        System.out.println("请输入用户名");
        String name = scanner.next();
        System.out.println("请输入密码");
        String password = scanner.next();
        String info = name+" "+password;

        File file = new File("D:\\a.txt");
        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(info);
        bufferedWriter.newLine();
        bufferedWriter.close();




    }

    public static void login() throws IOException {
        System.out.println("请输入用户名");
        String name = scanner.next();
        System.out.println("请输入密码");
        String password = scanner.next();
        String info = name+" "+password;



        FileReader fileReader = new FileReader("D:\\a.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = null;
        boolean isLogin = false;  //用于记录是否登录成功
        while ((line = bufferedReader.readLine())!= null){
            if (info.equals(line)){
                isLogin = true;
                break;
            }
        }
        if (isLogin){
            System.out.println("欢迎"+name+"登陆成功");
        }else{
            System.out.println("用户不存在，请重新输入");
        }
        bufferedReader.close();
    }

}
