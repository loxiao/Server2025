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

@WebServlet("/admin/Register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String  name=req.getParameter("a_name");
        String  pwd=req.getParameter("a_pwd");
        String  phone=req.getParameter("a_phone");
        String  pic=req.getParameter("a_pic");

        Admin admin= new Admin(-1,name,pwd,phone,pic,0);

        AdminService adminService=new AdminServiceImpl();
        Admin  max=adminService.register(admin);

        String  str="";
        if(max!=null)
            str=max.getA_id()+"";
        else
            str="-1";

        PrintWriter pw=resp.getWriter();
        pw.write(str);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}