package jsjweb.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import jsjweb.entity.Admin;
import jsjweb.service.AdminService;
import jsjweb.service.impl.AdminServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String name = req.getParameter("a_name");
        String pwd = req.getParameter("a_pwd");

        AdminService adminService = new AdminServiceImpl();
        Admin admin = adminService.login(name, pwd);


        PrintWriter pw=resp.getWriter();
        pw.write(JSON.toJSONString(admin)); // 返回JSON格式

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
