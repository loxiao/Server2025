package jsjweb.service;

import jsjweb.entity.Admin;

import java.util.List;

public interface AdminService {
    public Admin            login(String name, String pwd);

    public Admin            register(Admin user);

    public String           edit(Admin user);

    public String           addpic(int  id,String  pic);

    public String           delete(int aid);

    public List<Admin>      getAll(String  name);

    public Admin            select(int id);
}
