package jsjweb.dao;

import jsjweb.entity.Admin;
import java.sql.SQLException;
import java.util.List;

public interface AdminDao {

    public  int     insert(Admin user)    throws SQLException;
    public  int     delete(int id)    throws SQLException;
    public  int     update(Admin user)    throws SQLException;
    public  int     addpic(int  id,String  pic)    throws SQLException;
    public  Admin   select(String name)    throws SQLException;
    public  Admin   select(int id)    throws SQLException;
    public  Admin   selectMaxId( )    throws SQLException;

    public List<Admin> selectAll()                     throws SQLException;
    public List<Admin> selectAll(String  name)          throws SQLException;
}
