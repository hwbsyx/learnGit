package Jdbc.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

//使用druid连接池
public class DruidDemo {
/**
 /  1 导入jar包
 *  2 定义配置文件
 *  3 加载配置文件
 */


    public static void main(String[] args) throws Exception {
        druid();
    }


    public static void druid() throws Exception {
        Properties prop = new Properties();
        InputStream in = DruidDemo.class.getResourceAsStream("/druid.properties");
        prop.load(in);
        /*
        * 4 获取数据库连接池对象，通过工厂获取
        * */
        DataSource ds = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}
