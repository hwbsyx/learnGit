package Jdbc.selecttest;

import com.mysql.cj.protocol.Resultset;
import jdbcutil.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectTest {
    /*封装一个student类
    *
    *
    * */



    public static void main(String[] args) {
        List<Student> list = new SelectTest().findAll();
        System.out.println(list);
        System.out.println(list.size());
    }

    public List<Student> findAll(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Student> list = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "SELECT * FROM `student`";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            Student stu = null;
            list = new ArrayList<Student>();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String chinese = rs.getString("chinese");
                String english = rs.getString("english");
                String math = rs.getString("math");

                stu = new Student();
                stu.setId(id);
                stu.setName(name);
                stu.setChinese(chinese);
                stu.setEnglish(english);
                stu.setMath(math);

                list.add(stu);

            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            JdbcUtil.close(conn,pstmt,rs);
            return list;
        }

    }
}
