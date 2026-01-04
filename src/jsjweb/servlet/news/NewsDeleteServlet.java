package jsjweb.servlet.news;

import jsjweb.service.NewsService;
import jsjweb.service.impl.NewsServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/news/delete")
public class NewsDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String idParam = req.getParameter("n_id");
        String result = "删除失败：参数错误";

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                NewsService service = new NewsServiceImpl();
                result = service.delete(id);
            } catch (NumberFormatException e) {
                result = "删除失败：ID格式错误";
            }
        }

        PrintWriter pw = resp.getWriter();
        pw.write(result);
    }
}