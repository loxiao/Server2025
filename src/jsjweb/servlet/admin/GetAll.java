package jsjweb.servlet.admin;

import com.alibaba.fastjson.JSONArray;
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
import java.util.List;


@WebServlet("/admin/GetAll")
public class GetAll extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        try {
            AdminService service=new AdminServiceImpl();
            List<Admin> list=null;
            list=service.getAll(null);

            //---------List<Object>转json字符串
            String  str= JSONArray.toJSONString(list);

            PrintWriter pw=resp.getWriter();
            pw.write(str);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}