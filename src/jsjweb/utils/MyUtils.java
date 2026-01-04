package jsjweb.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MyUtils {
    private static DataSource ds;

    static{
        try {
            //1.加载配置文件
            Properties pro = new Properties();
            pro.load(MyUtils.class.getClassLoader().getResourceAsStream("database.properties"));

            //2.获取DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     */
    public static Connection getConnection(){
        Connection con;
        try {
            con = ds.getConnection();
        }
        catch (Exception ex){
            con = null;
        }
        return con;
    }

    /**
     * 释放资源
     */
    public static void close(Statement stmt, Connection conn){
        closeAll(null,stmt,conn);
    }

    public static void closeAll(ResultSet rs , Statement stmt, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();//归还连接

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
