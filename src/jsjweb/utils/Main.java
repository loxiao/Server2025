package jsjweb.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import jsjweb.entity.Admin;
import jsjweb.service.AdminService;
import jsjweb.service.impl.AdminServiceImpl;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception{
        Main.ReadProperties();
    }

    //加载配置文件
    private static  void    ReadProperties() throws Exception {
        Properties pro = new Properties();
        InputStream is = Main.class.getClassLoader().getResourceAsStream("database.properties");
        pro.load(is);

        //获取连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        //获取连接
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }

    private static  String    AddAdmin() throws Exception {
        Admin admin= new Admin(-1,"yhw","123","0258963","a3.png",0);

        AdminService adminService=new AdminServiceImpl();
        Admin  max=adminService.register(admin);

        String  str="";
        if(max!=null)
            str=max.getA_id()+"";
        else
            str="-1";

        return str;
    }
}
