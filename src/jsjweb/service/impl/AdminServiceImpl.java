package jsjweb.service.impl;

import jsjweb.dao.AdminDao;
import jsjweb.dao.impl.AdminDaoImpl;
import jsjweb.entity.Admin;
import jsjweb.service.AdminService;
import jsjweb.utils.MyUtils;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao=new AdminDaoImpl();

    @Override
    public Admin login(String name, String pwd) {
        Admin   result=null;

        try {
            Admin   admin=adminDao.select(name);
            if(admin!=null){
                if(admin.getA_pwd().equals(pwd)) {
                    result = admin;
                    result.setA_state(4);
                }else
                    result=new Admin(-1,"000","000","000","000",3);
            }else {
                result=new Admin(-1,"000","000","000","000",2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Admin register(Admin user) {
        Admin   result=null;

        try {
            Admin  admin=adminDao.select(user.getA_name());
            if(admin==null) {
                int row = adminDao.insert(user);
                if (row > 0) {
                    result=adminDao.selectMaxId();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String edit(Admin user) {
        String   result="no";

        try {
            int row = adminDao.update(user);
            if (row > 0) {
                result = "yes";
            }
        } catch (SQLException e) {
            result="no";
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String addpic(int id, String pic) {
        String   result="no";

        try {
            int row = adminDao.addpic(id,pic);
            if (row > 0) {
                result = "yes";
            }
        } catch (SQLException e) {
            result="no";
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String delete(int aid) {
        String   result="no";

        try {
            int row = adminDao.delete(aid);
            if (row > 0) {
                result = "yes";
            }
        } catch (SQLException e) {
            result="no";
            e.printStackTrace();
        }

        return result;
    }


    @Override
    public List<Admin> getAll(String  name) {
        List<Admin> result = null;

        try {
            if (name == null || name.trim() == "")
                result = adminDao.selectAll();
            else
                result = adminDao.selectAll(name);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Admin select(int id) {
        Admin   result=null;

        try {
            result=adminDao.select(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
