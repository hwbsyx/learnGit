package Jdbc.druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DruidDemo2 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "insert into student(name,chinese,english,math) values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"何文博");
            pstmt.setString(2,"100");
            pstmt.setString(3,"100");
            pstmt.setString(4,"100");

            int count = pstmt.executeUpdate();
            System.out.println(count);


        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.close(conn,pstmt);
        }

    }
}
