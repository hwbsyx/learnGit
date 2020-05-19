package Jdbc.login;

import jdbcutil.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
//    键盘录入账号密码
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账号：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();

        boolean result = new Login().login(username, password);
        if (result){
            System.out.println("登陆成功！");

        }else  {
            System.out.println("用户名或密码错误，请重新输入！");
        }
    }

//    建立一个登录方法，返回成功或者失败
    public boolean login(String username,String password){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        if (username == null || password == null){
            return false;
        }
//        连接数据库
        try {
            conn = JdbcUtil.getConnection();
            String sql = "SELECT * FROM user WHERE username=? AND password=?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtil.close(conn,pstmt,rs);
        }


        return false;

    }
}
