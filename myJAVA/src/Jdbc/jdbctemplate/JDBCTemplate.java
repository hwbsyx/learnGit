package Jdbc.jdbctemplate;

import Jdbc.druid.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCTemplate {
    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());
        String sql = "update student set chinese='80' where name=?";
        int count = template.update(sql, "何文博");
        System.out.println(count);

    }
}
