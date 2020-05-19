package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatement {
    private static String url = "jdbc:mysql://localhost:3306/day1?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT%2B8";


    private static String user = "root";
    private static String password = "root";

    public static void test2() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);

//        String sql = "SELECT * FROM student WHERE name=?;";
        String sql = "SELECT * FROM student;";
        java.sql.PreparedStatement preparedStatement = conn.prepareStatement(sql);
//        preparedStatement.setString(1,"王五");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int chinese = resultSet.getInt("chinese");
            int english = resultSet.getInt("english");
            int math = resultSet.getInt("math");

            System.out.println(id+","+name+","+chinese+","+english+","+math);

        }

        preparedStatement.close();
        conn.close();


    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        PreparedStatement.test2();
    }
}
