package jsjweb.servlet.news;

import com.alibaba.fastjson.JSONArray;
import jsjweb.entity.News;
import jsjweb.service.NewsService;
import jsjweb.service.impl.NewsServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/news/list")
public class NewsListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        int page = Integer.parseInt(req.getParameter("page") == null ? "1" : req.getParameter("page"));
        int pageSize = 10; // 每页10条
        NewsService service = new NewsServiceImpl();
        List<News> newsList = service.getAllNews(page, pageSize);

        PrintWriter pw = resp.getWriter();
        pw.write(JSONArray.toJSONString(newsList));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}