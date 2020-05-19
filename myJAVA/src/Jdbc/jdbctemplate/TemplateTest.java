package Jdbc.jdbctemplate;

import Jdbc.druid.JDBCUtil;
import Jdbc.selecttest.Student;
import com.sun.jdi.VMOutOfMemoryException;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class TemplateTest {
    DataSource ds = JDBCUtil.getDataSource();

//    修改student数据的一条成绩
    @org.junit.Test
    public void test1(){
        JdbcTemplate template = new JdbcTemplate(ds);
        String sql = "update student set math=90 where name=?";
        int count = template.update(sql, "丁守旺");
        System.out.println(count);

    }

//    添加一条记录
    @Test
    public void test2(){
        JdbcTemplate template = new JdbcTemplate(ds);
        String sql = "insert into student(name,chinese,english,math) values(?,?,?,?)";
        int count = template.update(sql, "昌吉", "10", "20", "30");
        System.out.println(count);

    }
//删除刚才添加的记录
    @Test
    public void test3(){
        JdbcTemplate template = new JdbcTemplate(ds);
        String sql = "delete from student where name=?";
        int count = template.update(sql, "何文博");
        System.out.println(count);

    }
//    查询id=2的记录，封装为map集合
//    只能封装长度为1的结果
    @Test
    public void test4(){
        JdbcTemplate template = new JdbcTemplate(ds);
        String sql = "select * from student where id=?";
        Map<String, Object> map = template.queryForMap(sql,2);
        System.out.println(map);
    }
//    查询所有记录。封装为list
    @Test
    public void test5(){
        JdbcTemplate template = new JdbcTemplate(ds);
        String sql = "select * from student;";
        List<Map<String, Object>> list = template.queryForList(sql);
        for (Map map:list){

            System.out.println(map);
        }


    }
//    查询所有记录将其封装为student对象的list集合
    @Test
    public void test6(){
        JdbcTemplate template = new JdbcTemplate(ds);
        String sql = "select * from student;";
        List<Student> listmaps = template.query(sql, new BeanPropertyRowMapper<Student>(Student.class));
        for (Student listmap : listmaps){
            System.out.println(listmap);
        }

    }
    @Test
    public void test7(){
        JdbcTemplate template = new JdbcTemplate(ds);
        String sql = "select count(id) from student";
        Long count = template.queryForObject(sql, Long.class);
        System.out.println(count);



    }
}
