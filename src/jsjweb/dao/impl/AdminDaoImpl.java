package jsjweb.dao.impl;

import jsjweb.dao.AdminDao;
import jsjweb.entity.Admin;
import jsjweb.utils.MyUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl  implements AdminDao {
    private QueryRunner  queryRunner=new QueryRunner();

    @Override
    public int insert(Admin user) throws SQLException {
        Connection  conn=null;
        String      sql="";
        int         row=0;
        Object[]    params = {
                user.getA_name(),user.getA_pwd(),
                user.getA_phone(),user.getA_pic()
        };

        conn= MyUtils.getConnection();
        sql = "insert into admin(a_name,a_pwd,a_phone,a_pic) values(?,?,?,?)";

        row = queryRunner.update(conn,sql,params);

        MyUtils.closeAll(null,null,conn);

        return row;
    }

    @Override
    public int delete(int id) throws SQLException {
        Connection  conn=null;
        String      sql="";
        int         row=0;

        conn= MyUtils.getConnection();
        sql = "delete from admin where a_id=?";

        row = queryRunner.update(conn,sql,id);

        MyUtils.closeAll(null,null,conn);

        return row;
    }

    @Override
    public int update(Admin user) throws SQLException {
        Connection  conn=null;
        String      sql="";
        int         row=0;
        
        conn= MyUtils.getConnection();
        
        // 根据密码是否为null动态构建SQL语句
        if(user.getA_pwd() == null) {
            // 不更新密码字段
            String[]    params = {user.getA_name(),user.getA_phone(),user.getA_pic(), String.valueOf(user.getA_id())};
            sql = "update admin set a_name=?,a_phone=?,a_pic=? where a_id=?";
            row = queryRunner.update(conn,sql,params);
        } else {
            // 更新所有字段，包括密码
            String[]    params = {user.getA_name(),user.getA_pwd(),user.getA_phone(),user.getA_pic(), String.valueOf(user.getA_id())};
            sql = "update admin set a_name=?,a_pwd=?,a_phone=?,a_pic=? where a_id=?";
            row = queryRunner.update(conn,sql,params);
        }

        MyUtils.closeAll(null,null,conn);

        return row;
    }

    @Override
    public int addpic(int id,String  pic) throws SQLException {
        Connection  conn=null;
        String      sql="";
        int         row=0;
        Object[]    params = {pic,id};

        conn= MyUtils.getConnection();
        sql = "update admin set a_pic=? where a_id=?";

        row = queryRunner.update(conn,sql,params);

        MyUtils.closeAll(null,null,conn);

        return row;
    }

    @Override
    public Admin select(String name) throws SQLException {
        Admin       admin=null;
        Connection  conn=null;
        String      sql="";

        conn= MyUtils.getConnection();
        sql="select * from admin where a_name=?";

        //BeanHandler：将查询结果的第一行封装成指定的数据类型
        admin=queryRunner.query(conn,sql,new BeanHandler<Admin>(Admin.class),name);

        MyUtils.closeAll(null,null,conn);

        return admin;
    }

    @Override
    public Admin select(int id) throws SQLException {
        Admin       admin=null;
        Connection  conn=null;
        String      sql="";

        conn= MyUtils.getConnection();
        sql="select * from admin where a_id=?";

        //BeanHandler：将查询结果的第一行封装成指定的数据类型
        admin=queryRunner.query(conn,sql,new BeanHandler<Admin>(Admin.class),id);

        MyUtils.closeAll(null,null,conn);

        return admin;
    }

    @Override
    public Admin selectMaxId( ) throws SQLException {
        Admin       admin=null;
        Connection  conn=null;
        String      sql="";

        conn= MyUtils.getConnection();
        sql="select * from admin order by a_id desc limit 1";

        //BeanHandler：将查询结果的第一行封装成指定的数据类型
        admin=queryRunner.query(conn,sql,new BeanHandler<Admin>(Admin.class));

        MyUtils.closeAll(null,null,conn);

        return admin;
    }

    @Override
    public List<Admin> selectAll() throws SQLException {
        List<Admin>     admins=null;
        Connection      conn=null;
        String          sql="";

        conn= MyUtils.getConnection();
        sql="select * from admin order by a_id";

        //BeanListHandler：将查询结果的每一行封装成指定类型的List集合
        admins=queryRunner.query(conn,sql,new BeanListHandler<Admin>(Admin.class));

        MyUtils.closeAll(null,null,conn);

        return admins;
    }

    @Override
    public List<Admin> selectAll(String name) throws SQLException {
        List<Admin>     admins=null;
        Connection conn=null;
        String          sql="";

        conn= MyUtils.getConnection();
        sql="select * from admin where a_name like '%"+name+"%'"+" order by a_id";

        //BeanListHandler：将查询结果的每一行封装成指定类型的List集合
        admins=queryRunner.query(conn,sql,new BeanListHandler<Admin>(Admin.class));

        MyUtils.closeAll(null,null,conn);

        return admins;
    }
}
