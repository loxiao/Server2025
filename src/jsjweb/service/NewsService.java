package jsjweb.service;

import jsjweb.entity.News;
import java.util.List;

public interface NewsService {
    // 发布新闻
    boolean publish(News news);
    // 删除新闻
    String delete(int id);
    // 更新新闻
    String update(News news);
    // 获取单条新闻
    News getNewsById(int id);
    // 按类型获取新闻
    List<News> getNewsByType(int type);
    // 分页获取所有新闻
    List<News> getAllNews(int page, int pageSize);
    // 搜索新闻
    List<News> searchNews(String keyword);
}