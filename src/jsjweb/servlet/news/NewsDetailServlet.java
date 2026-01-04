package jsjweb.servlet.news;

import com.alibaba.fastjson.JSON;
import jsjweb.entity.News;
import jsjweb.service.NewsService;
import jsjweb.service.impl.NewsServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/news/detail")
public class NewsDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        int id = Integer.parseInt(req.getParameter("id"));
        NewsService service = new NewsServiceImpl();
        News news = service.getNewsById(id);

        PrintWriter pw = resp.getWriter();
        pw.write(JSON.toJSONString(news));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}