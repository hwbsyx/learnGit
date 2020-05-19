package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {
    private static String url = "jdbc:mysql://localhost:3306/day1?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT%2B8";


    private static String user = "root";
    private static String password = "root";

    public static void test1() throws SQLException, ClassNotFoundException {
//        驱动程序注册
        Class.forName("com.mysql.cj.jdbc.Driver");
//        获取连接对象


        Connection connection = DriverManager.getConnection(url,user,password);

//        创建statement
        Statement statement = connection.createStatement();
//        准备sql
        String sql = "CREATE TABLE employee(\n" +
                "\tid INT PRIMARY KEY auto_increment,\n" +
                "\tNAME VARCHAR(20),\n" +
                "\tgender VARCHAR(2),\n" +
                "\tage INT,\n" +
                "\tpost VARCHAR(20),\n" +
                "\temail VARCHAR(20),\n" +
                "\ttelephone INT\n" +
                "\n" +
                ")";
//        发送sql语句，执行sql语句
        int count= statement.executeUpdate(sql);
//          输出结果
        System.out.println("影响了"+count+"行");
//        关闭资源
        statement.close();
        connection.close();

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JDBCTest.test1();
    }



}
