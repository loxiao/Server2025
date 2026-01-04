package jsjweb.servlet.admin;

import com.alibaba.fastjson.JSON;
import jsjweb.entity.Admin;
import jsjweb.service.AdminService;
import jsjweb.service.impl.AdminServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/admin/Edit")
public class Edit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String  id=req.getParameter("a_id");
        String  name=req.getParameter("a_name");
        String  pwd=req.getParameter("a_pwd");
        String  phone=req.getParameter("a_phone");
        String  pic=req.getParameter("a_pic");

        int idd=Integer.parseInt(id);

        // 当密码为空时，将其设置为null，以便在DAO层不更新密码
        Admin admin= new Admin(idd,name,(pwd==null || pwd.trim().isEmpty())?null:pwd,phone,pic,0);

        AdminService adminService = new AdminServiceImpl();
        String  str               = adminService.edit(admin);


        PrintWriter pw=resp.getWriter();
        pw.write(str);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}