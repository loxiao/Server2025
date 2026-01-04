package jsjweb.servlet.news;

import jsjweb.entity.News;
import jsjweb.service.NewsService;
import jsjweb.service.impl.NewsServiceImpl;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/news/publish")
@MultipartConfig
public class NewsPublishServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        News news = new News();
        news.setN_title(req.getParameter("title"));
        news.setN_content(req.getParameter("content"));
        news.setN_author(req.getParameter("author"));
        news.setN_type(Integer.parseInt(req.getParameter("type")));

        try {
            // 先尝试解析"yyyy-MM-dd HH:mm:ss"格式
            news.setN_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(req.getParameter("time")));
        } catch (ParseException e) {
            try {
                // 如果失败，尝试解析"yyyy-MM-dd'T'HH:mm"格式（datetime-local输入类型的默认格式）
                news.setN_time(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(req.getParameter("time")));
            } catch (ParseException ex) {
                news.setN_time(new Date()); // 默认当前时间
            }
        }

        // 处理文件上传
        try {
            Part part = req.getPart("pic");
            if (part != null && part.getSize() > 0) {
                // 获取上传文件名
                String fileName = part.getSubmittedFileName();
                if (fileName != null && !fileName.isEmpty()) {
                    // 保存文件到upimages目录
                    String savePath = "upimages";
                    String fullPath = req.getServletContext().getRealPath("/") + savePath;
                    File saveDir = new File(fullPath);
                    if (!saveDir.exists()) {
                        saveDir.mkdirs();
                    }

                    // 生成唯一文件名，避免覆盖
                    String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                    String filePath = fullPath + File.separator + uniqueFileName;
                    part.write(filePath);

                    // 设置图片路径
                    news.setN_pic(uniqueFileName);
                }
            } else {
                // 如果没有上传新图片，使用原图片路径
                news.setN_pic(req.getParameter("old_pic"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        NewsService service = new NewsServiceImpl();
        boolean success = service.publish(news);

        PrintWriter pw = resp.getWriter();
        pw.write(success ? "发布成功" : "发布失败");
    }
}