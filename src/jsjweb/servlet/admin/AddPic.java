package jsjweb.servlet.admin;

import jsjweb.service.AdminService;
import jsjweb.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/admin/AddPic")
public class AddPic extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        final String getPostType = req.getHeader("post_type");
        final String id          = req.getHeader("a_id");
        final String pic         = req.getHeader("a_pic");

        String  fname="upimages\\"+pic;
        String  result="yes";

        try {
            if (getPostType != null && getPostType.equals("feed_back_post")) {
                InputStream input = req.getInputStream();        //获取请求体的数据
                File file = null;

                file = new File(req.getServletContext().getRealPath("/") + fname);
                if (!file.exists())
                    file.createNewFile();
                BufferedOutputStream bufferout = null;
                FileOutputStream out = new FileOutputStream(file);        //文件输出流
                byte[] data = new byte[10240];
                int len = 0;
                StringBuffer strbuf = new StringBuffer();
                bufferout = new BufferedOutputStream(out);
                while ((len = input.read(data)) != -1) {
                    bufferout.write(data, 0, len);
                }

                if (bufferout != null)
                    bufferout.close();
                if (out != null)
                    out.close();
                if (input != null)
                    input.close();
            }
        }catch (Exception e){
            result="no";
        }


        //----------------------------------------
//        int idd=Integer.parseInt(id);
//        AdminService adminService=new AdminServiceImpl();
//        String  str=adminService.addpic(idd,pic);

        PrintWriter pw=resp.getWriter();
        pw.write(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}